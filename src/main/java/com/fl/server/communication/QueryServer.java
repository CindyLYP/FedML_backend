package com.fl.server.communication;

import com.fl.server.mapper.DatasetMapper;
import com.fl.server.mapper.NodeMapper;
import com.fl.server.mapper.SceneMapper;
import com.fl.server.mapper.UtilsMapper;
import com.fl.server.pojo.Dataset;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
@Service
public class QueryServer {
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

    public String queryStatus(String name){
        String api = queryStatusApi+"?name="+name;
        String res = new String();
        try{
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<JSONObject> response = restTemplate.getForEntity(api, JSONObject.class);
            JSONObject data = response.getBody();
            res = (String) data.get("msg");

        }catch (HttpClientErrorException e){
            System.out.println("queryStatusApi http post error!");
        }
        finally {
            return res;
        }
    }

    @Async
    public JSONObject queryDataset(Dataset dataset) throws InterruptedException {
        JSONObject res=new JSONObject();
        String api = queryDatasetApi+"?task_name="+dataset.getDatasetName();
        while(true){
            Thread.currentThread().sleep(10000);
            String status = queryStatus(dataset.getDatasetName());
            if(status.equals("Finished")) break;
        }

        try{
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<JSONObject> response = restTemplate.getForEntity(api, JSONObject.class);
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
