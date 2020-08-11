package com.fl.server.communication;

import com.fl.server.mapper.DatasetMapper;
import com.fl.server.mapper.NodeMapper;
import com.fl.server.mapper.SceneMapper;
import com.fl.server.mapper.UtilsMapper;
import com.fl.server.object.tools.Randm;
import com.fl.server.pojo.Dataset;
import com.fl.server.pojo.Node;
import com.fl.server.pojo.Scene;
import com.fl.server.pojo.Task;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class MainServer {
    @Value("${model_server.url}")
    private String url;

    private final String TaskApi = "/createTask";
    private final String StartApi = "/startTask";

    @Autowired
    private SceneMapper sceneMapper;
    @Autowired
    private DatasetMapper datasetMapper;
    @Autowired
    private NodeMapper nodeMapper;
    @Autowired
    private UtilsMapper utilsMapper;
    @Autowired
    private QueryServer queryServer;

    public void startTask(String name){
        String api = StartApi+"?task_name="+name;
        try{
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<HashMap> response = restTemplate.getForEntity(url+api, HashMap.class);
            HashMap data = response.getBody();
            if ("ok".equals(data.get("status"))){
                System.out.println(data.get("msg"));
            }

        }catch (HttpClientErrorException e){
            System.out.println("queryStatusApi http post error!");
        }
        finally {
            System.out.println("start task successfully");
        }
    }

    public HashMap alignDataset(Dataset dataset){
        HashMap res = new HashMap();
        HashMap<String,Object> data = new HashMap<>();
        data.put("task_name",dataset.getDatasetName());
        ArrayList<HashMap<String,Object>> clients = new ArrayList<>();
        Scene scene = sceneMapper.selectBySceneName(utilsMapper.IdToSceneName(dataset.getSceneId())).get(0);

        Node mainNode = nodeMapper.findNode(scene.getInstitution()).get(0);
        Node labelNode = nodeMapper.findNode(scene.getInstitution()).get(0);
        HashMap<String ,Object> clientConfig = new HashMap<>();
        clientConfig.put("client_type","alignment_main");
        clientConfig.put("computation_port", Randm.randomPort());
        HashMap<String,Object> client = new HashMap<>();
        client.put("role","main_client");
        client.put("addr",mainNode.getIpAddress());
        client.put("http_port",Integer.parseInt(mainNode.getPort()));
        client.put("client_config",clientConfig);
        clients.add(client);

        for(HashMap<String,Object> item:dataset.getDict()){
            Node node = nodeMapper.findNode((String)item.get("provider")).get(0);
            clientConfig = new HashMap<>();;
            client = new HashMap<>();;
            clientConfig.put("client_type","alignment_data");
            clientConfig.put("computation_port",Randm.randomPort());
            clientConfig.put("raw_data_path",node.getCsvPath());
            clientConfig.put("out_data_path",Randm.outCsvPath());
            clientConfig.put("columns", item.get("attributes"));
            client.put("role","feature_client");
            client.put("addr",node.getIpAddress());
            client.put("http_port",Integer.parseInt(node.getPort()));
            client.put("client_config",clientConfig);
            clients.add(client);
        }
        clientConfig = new HashMap<>();;
        client = new HashMap<>();;
        clientConfig.put("client_type","alignment_data");
        clientConfig.put("computation_port",Randm.randomPort());
        clientConfig.put("raw_data_path",labelNode.getCsvPath());
        clientConfig.put("out_data_path",Randm.outCsvPath());
        ArrayList<String> col = new ArrayList<>();
        col.add(scene.getTarget());
        clientConfig.put("columns",col);
        client.put("role","label_client");
        client.put("addr",labelNode.getIpAddress());
        client.put("http_port",Integer.parseInt(labelNode.getPort()));
        client.put("client_config",clientConfig);
        clients.add(client);
        data.put("clients",clients);
        utilsMapper.insertServerMap(dataset.getDatasetName(),new JSONObject(data).toString());
        try{
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<HashMap<String, Object>> request = new HttpEntity<>(data, headers);
            ResponseEntity<HashMap> response = restTemplate.postForEntity(url+TaskApi, request,HashMap.class);
            res= response.getBody();
            System.out.print("create align task: ");
            System.out.println(res);
            startTask((String) data.get("task_name"));
            System.out.println("-----------------------------------------");
            System.out.println("start a thread to listen align task");
            queryServer.queryDataset(dataset);


        }catch (HttpClientErrorException e){
            System.out.println("http post error!");
        }
        finally {
            return res;
        }

    }

    public JSONObject trainTask(Task task) throws JSONException {
        HashMap res = new HashMap();
        Dataset dataset = datasetMapper.selectByDatasetName(utilsMapper.IdToDatasetName(task.getDatasetId())).get(0);
        JSONObject data = new JSONObject(utilsMapper.selectServerMap(dataset.getDatasetName()));
        data.put("task_name",task.getTaskName());
        JSONArray clients = (JSONArray) data.get("clients");
        JSONArray newClients = new JSONArray();

        JSONObject mainClient = (JSONObject) clients.get(0);
        JSONObject clientConfig = (JSONObject) mainClient.get("client_config");
        clientConfig.put("client_type","shared_nn_main");
        clientConfig.put("in_dim",64);
        clientConfig.put("out_dim",1);
        clientConfig.put("layers",new JSONArray().put(1));
        clientConfig.put("test_per_batches",101);
        clientConfig.put("max_iter",12345);
        mainClient.put("client_config",clientConfig);
        newClients.put(mainClient);

        JSONObject crypto_client = new JSONObject();
        crypto_client.put("role","crypto_producer");
        crypto_client.put("addr",mainClient.get("addr"));
        crypto_client.put("http_port",6666);
        JSONObject config = new JSONObject();
        config.put("client_type","triplet_producer");
        config.put("computation_port",Randm.randomPort());
        JSONArray listen_clients = new JSONArray();
        for(int i=1;i<clients.length()-1;i++){
            listen_clients.put(i+1);
        }
        config.put("listen_clients",listen_clients);
        crypto_client.put("client_config",config);
        newClients.put(crypto_client);
        int i=1;
        for(;i<clients.length()-1;i++){
            JSONObject client= (JSONObject) clients.get(i);
            config = (JSONObject) client.get("client_config");
            config.put("client_type","shared_nn_feature");
            config.put("data_path",config.get("out_data_path"));
            config.remove("raw_data_path");
            config.remove("out_data_path");
            config.remove("columns");
            client.put("client_config",config);
            newClients.put(client);
        }
        JSONObject client= (JSONObject) clients.get(i);
        config = (JSONObject) client.get("client_config");
        config.put("client_type","shared_nn_label");
        config.put("data_path",config.get("out_data_path"));
        config.put("loss","mse");
        config.put("metric","auc_ks");
        config.remove("raw_data_path");
        config.remove("out_data_path");
        config.remove("columns");
        client.put("client_config",config);
        newClients.put(client);
        data.put("clients",newClients);
        try{
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(data.toString(), headers);
            ResponseEntity<HashMap> response = restTemplate.postForEntity(url+TaskApi, request, HashMap.class);
            res = response.getBody();
            System.out.print("create train task: ");
            System.out.println(res);
            startTask((String) data.get("task_name"));
            System.out.println("-----------------------------------------");
            System.out.println("start a thread to listen train task");

            queryServer.queryTask(task);

        }catch (HttpClientErrorException e){
            System.out.println("http post error!");
        }
        finally {
            return new JSONObject(res);
        }
    }


}
