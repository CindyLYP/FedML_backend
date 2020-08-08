package com.fl.server.communication;

import com.fl.server.mapper.*;
import com.fl.server.pojo.Dataset;
import com.fl.server.pojo.Task;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
@Service
public class QueryServer {
    private final String queryStatusApi = "http://10.214.192.22:8380/queryStatus";
    private final String queryDatasetApi = "http://10.214.192.22:8380/queryDataset";
    private final String queryTaskApi = "http://10.214.192.22:8380/queryTask";
    private final String updateDatasetApi = "http://localhost:8888/updateDataset";

    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private DatasetMapper datasetMapper;


    public String queryStatus(String name){
        String api = queryStatusApi+"?task_name="+name;
        String res = new String();
        try{
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<HashMap> response = restTemplate.getForEntity(api, HashMap.class);
            HashMap data = response.getBody();
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
        HashMap res=new HashMap();
        String api = queryDatasetApi+"?task_name="+dataset.getDatasetName();
        while(true){
            Thread.currentThread().sleep(2000);
            String status = queryStatus(dataset.getDatasetName());
            System.out.println("querying task status: "+status);
            if(status.equals("Finished")){
                System.out.println("align task finish,start update dataset aligned_num");
                break;
            }
            if(status.equals("Failed")) {
                System.out.println("align task failed");
                return ;
            }
        }

        try{
            Thread.sleep(2000);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<HashMap> response = restTemplate.getForEntity(api, HashMap.class);
            res = response.getBody();
            System.out.println("get dataset align number from server");
            System.out.println(res.toString());
            ArrayList<Integer> alignNum = (ArrayList<Integer>) res.get("msg");
            dataset.setAlignedNum(alignNum.get(0));
            dataset.setId(datasetMapper.selectByDatasetName(dataset.getDatasetName()).get(0).getId());
            datasetMapper.update(dataset);
            System.out.println("update database done");
            System.out.println("Thread is finished");
            System.out.println("-----------------------------------------");
            /*
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Dataset> request = new HttpEntity<>(dataset, headers);
            ResponseEntity<String> resFromDB = restTemplate.postForEntity(updateDatasetApi, request,String.class);
            System.out.println(resFromDB.getBody());
             */

        }catch (HttpClientErrorException e){
            System.out.println("http post error!");
        }
        finally {
            return ;
        }
    }

    @Async
    public void queryTask(Task task) throws InterruptedException {
        HashMap res=new HashMap();
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
            ResponseEntity<HashMap> response = restTemplate.getForEntity(api, HashMap.class);
            res = response.getBody();
            System.out.println("get query train response from server");
            ArrayList<ArrayList<Object>> msg = (ArrayList<ArrayList<Object>>) res.get("msg");
            String trainInfo = new String();
            for (int i=0;i<msg.size();i++){
                for (int j=0;j<msg.get(i).size();j++)
                    trainInfo+=msg.get(i).get(j)+"|";
                trainInfo = trainInfo.substring(0,trainInfo.length()-1)+"#";
            }
            task.setTrainInfo(trainInfo.substring(0,trainInfo.length()-1));
            taskMapper.update(task);
        }catch (HttpClientErrorException e){
            System.out.println("http post error!");
        }
        finally {
            return ;
        }
    }
}
