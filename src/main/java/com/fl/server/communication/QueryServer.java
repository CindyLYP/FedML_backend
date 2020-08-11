package com.fl.server.communication;

import com.fl.server.mapper.*;
import com.fl.server.pojo.Dataset;
import com.fl.server.pojo.Task;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${model_server.url}")
    private String url;

    private final String queryStatusApi = "/queryStatus";
    private final String queryDatasetApi = "/queryDataset";
    private final String queryTaskApi = "/queryTask";

    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private DatasetMapper datasetMapper;


    public String queryStatus(String name){
        String api = queryStatusApi+"?task_name="+name;
        String res = new String();
        try{
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<HashMap> response = restTemplate.getForEntity(url+api, HashMap.class);
            HashMap data = response.getBody();
            res = (String) data.get("msg");

        }catch (HttpClientErrorException e){
            System.out.println("queryStatusApi http post error!");
        }
        finally {
            return res;
        }
    }
    public void queryTaskMetrics(Task task){
        String api = queryTaskApi+"?task_name="+task.getTaskName()+"&query=record&client=-1";

        try{
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<HashMap> response = restTemplate.getForEntity(url+api, HashMap.class);
            HashMap res = response.getBody();
            System.out.println("get train info response from server");
            ArrayList<ArrayList<Object>> msg = (ArrayList<ArrayList<Object>>) res.get("msg");
            String trainInfo = new String();
            for (int i=0;i<msg.size();i++){
                for (int j=0;j<msg.get(i).size();j++){
                    String tmp = new String();
                    if (j==1)
                        tmp = String.valueOf(msg.get(i).get(j));
                    else
                        tmp = String.valueOf(msg.get(i).get(j)).substring(0,5);
                    trainInfo+=tmp+"|";
                }

                trainInfo = trainInfo.substring(0,trainInfo.length()-1)+"#";
            }
            task.setTrainInfo(trainInfo.substring(0,trainInfo.length()-1));
            taskMapper.delete(task.getTaskName());
            taskMapper.insert(task);
            System.out.println("update database done");

        }catch (HttpClientErrorException e){
            System.out.println("http post error!");
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
            ResponseEntity<HashMap> response = restTemplate.getForEntity(url+api, HashMap.class);
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
        while(true){
            Thread.currentThread().sleep(2000);
            String status = queryStatus(task.getTaskName());
            System.out.println("task status: "+status);
            if(status.equals("Finished")){
                System.out.println("train task finish,update database");
                task.setTaskStatus("训练完成");
                queryTaskMetrics(task);
                System.out.println("Thread is finished");
                System.out.println("-----------------------------------------");
                break;
            }

            if(status.equals("Running")) {
                System.out.println("train task status: "+status+" update task metric in database");
                task.setTaskStatus("正在训练");
                queryTaskMetrics(task);
            }


            if(status.equals("Failed")) {
                System.out.println("train task failed");
                return ;
            }
        }
        return ;
    }
}
