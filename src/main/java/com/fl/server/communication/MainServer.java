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
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainServer {
    private final String TaskApi = "http://10.214.192.22:8080/CreateTask";
    private final String queryStatusApi = "http://10.214.192.22:8080/queryStatus";
    private final String queryDatasetApi = "http://10.214.192.22:8080/queryDataset";
    private final String queryTaskApi = "http://10.214.192.22:8080/queryTask";
    @Autowired
    private SceneMapper sceneMapper;
    @Autowired
    private DatasetMapper datasetMapper;
    @Autowired
    private NodeMapper nodeMapper;
    @Autowired
    private UtilsMapper utilsMapper;

    public JSONObject alignDataset(Dataset dataset){
        JSONObject res = new JSONObject();
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
        client.put("http_port",mainNode.getPort());
        client.put("client_config",clientConfig);
        clients.add(client);

        for(HashMap<String,Object> item:dataset.getDict()){
            Node node = nodeMapper.findNode((String)item.get("provider")).get(0);
            clientConfig.clear();
            client.clear();
            clientConfig.put("client_type","alignment_data");
            clientConfig.put("computation_port",Randm.randomPort());
            clientConfig.put("raw_data_path",node.getCsvPath());
            clientConfig.put("out_data_path",Randm.outCsvPath());
            clientConfig.put("columns", item.get("attributes"));
            client.put("role","feature_client");
            client.put("addr",node.getIpAddress());
            client.put("http_port",node.getPort());
            client.put("client_config",clientConfig);
            clients.add(client);
        }
        clientConfig.clear();
        client.clear();
        clientConfig.put("client_type","alignment_data");
        clientConfig.put("computation_port",Randm.randomPort());
        clientConfig.put("raw_data_path",labelNode.getCsvPath());
        clientConfig.put("out_data_path",Randm.outCsvPath());
        clientConfig.put("columns",new ArrayList<String>().add(scene.getTarget()));
        client.put("role","label_client");
        client.put("addr",labelNode.getIpAddress());
        client.put("http_port",labelNode.getPort());
        client.put("client_config",clientConfig);
        clients.add(client);
        data.put("clients",clients);
        utilsMapper.insertServerMap(dataset.getDatasetName(),new JSONObject(data).toString());
        try{
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<HashMap<String, Object>> request = new HttpEntity<>(data, headers);
            ResponseEntity<JSONObject> response = restTemplate.postForEntity(TaskApi, request, JSONObject.class);
            res = response.getBody();
            System.out.println("get response");
            System.out.println(response.getBody());


        }catch (HttpClientErrorException e){
            System.out.println("http post error!");
        }
        finally {
            return res;
        }

    }

    public JSONObject trainTask(Task task) throws JSONException {
        Dataset dataset = datasetMapper.selectByDatasetName(utilsMapper.IdToDatasetName(task.getDatasetId())).get(0);
        JSONObject data = new JSONObject(utilsMapper.selectServerMap(dataset.getDatasetName()));
        data.put("task_name",task.getTaskName());
        ArrayList<HashMap<String,Object>> clients = (ArrayList<HashMap<String, Object>>) data.get("clients");

        HashMap<String,Object> mainClient = clients.get(0);
        HashMap<String,Object> clientConfig = (HashMap<String, Object>) mainClient.get("client_config");
        clientConfig.put("client_type","shared_nn_main");


        for(int i=0;i<clients.size();i++){

        }
        return new JSONObject();
    }

    public JSONObject query(String queryType, HashMap<String,Object> params){
        JSONObject res=new JSONObject();
        String api = new String();
        if (queryType.equals("status")) api= queryStatusApi;
        else if (queryType.equals("dataset")) api = queryDatasetApi;
        else if (queryType.equals("task")) api = queryTaskApi;
        else System.out.println("input queryType error, make sure the type is in [status,dataset,task]");
        api+="?";
        for(String key:params.keySet()){
            api+=key+"={"+key+"}&";
        }
        api = api.substring(0,api.length()-1);
        try{
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<JSONObject> response = restTemplate.getForEntity(api, JSONObject.class,params);
            res = response.getBody();
            System.out.println("get response");
            System.out.println(response.getBody());


        }catch (HttpClientErrorException e){
            System.out.println("http post error!");
        }
        finally {
            return res;
        }
    }

}
