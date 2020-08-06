package com.fl.server.communication;

import com.fl.server.mapper.DatasetMapper;
import com.fl.server.mapper.NodeMapper;
import com.fl.server.mapper.SceneMapper;
import com.fl.server.mapper.UtilsMapper;
import com.fl.server.pojo.Dataset;
import com.fl.server.pojo.Task;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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
    public void queryDataset(Dataset dataset) throws InterruptedException {
        JSONObject res=new JSONObject();
        String api = queryDatasetApi+"?task_name="+dataset.getDatasetName();
        while(true){
            Thread.currentThread().sleep(10000);
            String status = queryStatus(dataset.getDatasetName());
            if(status.equals("Finished")){
                System.out.println("align task finish");
                break;
            }
            if(status.equals("Failed")) {
                System.out.println("align task failed");
                return ;
            }
        }

        try{
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<JSONObject> response = restTemplate.getForEntity(api, JSONObject.class);
            res = response.getBody();
            System.out.println("get query dataset response from server");
            ArrayList<Integer> alignNum = (ArrayList<Integer>) res.get("msg");
            dataset.setAlignedNum(alignNum.get(0));
            datasetMapper.update(dataset);
        }catch (HttpClientErrorException e){
            System.out.println("http post error!");
        }
        finally {
            return ;
        }
    }

    @Async
    public void queryTask(Task task) throws InterruptedException {
        JSONObject res=new JSONObject();
        String api = queryTaskApi+"?task_name="+task.getTaskName()+"&query=record&client=-1";
        while(true){
            Thread.currentThread().sleep(10000);
            String status = queryStatus(task.getTaskName());
            if(status.equals("Finished")){
                System.out.println("train task finish");
                break;
            }
            if(status.equals("Failed")) {
                System.out.println("train task failed");
                return ;
            }
        }

        try{
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<JSONObject> response = restTemplate.getForEntity(api, JSONObject.class);
            res = response.getBody();
            System.out.println("get query train response from server");
            ArrayList<ArrayList<Double>> msg = (ArrayList<ArrayList<Double>>) res.get("msg");
        }catch (HttpClientErrorException e){
            System.out.println("http post error!");
        }
        finally {
            return ;
        }
    }
}
