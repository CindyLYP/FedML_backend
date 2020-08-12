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
    private static int newline = 0;

    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private DatasetMapper datasetMapper;
    @Autowired
    private UtilsMapper utilsMapper;


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
        HashMap res = new HashMap();
        try{
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<HashMap> response = restTemplate.getForEntity(url+api, HashMap.class);
            res = response.getBody();
            ArrayList<ArrayList<Object>> msg = (ArrayList<ArrayList<Object>>) res.get("msg");
            if (msg.size()==0 && res.get("status").equals("ok")) return;
            String trainInfo = new String();
            for (int i=0;i<msg.size();i++){
                for (int j=0;j<msg.get(i).size();j++){
                    String tmp = String.valueOf(msg.get(i).get(j));
                    if (tmp.length()<=5)
                        tmp = String.valueOf(msg.get(i).get(j));
                    else
                        tmp = String.valueOf(msg.get(i).get(j)).substring(0,5);
                    trainInfo+=tmp+"|";
                }

                trainInfo = trainInfo.substring(0,trainInfo.length()-1)+"#";
            }
            task.setTrainInfo(trainInfo.substring(0,trainInfo.length()-1));
            taskMapper.update(task);
        }catch (HttpClientErrorException |ClassCastException e){
            System.out.println("http post error!");
            System.out.println(res.toString());
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

        }catch (HttpClientErrorException e){
            System.out.println("http post error!");
        }
        finally {
            return ;
        }
    }

    @Async
    public void queryTask(Task task) throws InterruptedException {

        task.setId(taskMapper.selectByTaskName(task.getTaskName()).get(0).getId());
        task.setTaskStatus("正在训练");
        System.out.println("train task status is running update task metric in database");
        Thread.currentThread().sleep(4000);
        while(true){
            Thread.currentThread().sleep(6000);
            String status = queryStatus(task.getTaskName());
            if(status.equals("Finished")){
                System.out.println("\ntrain task finish,update database");
                task.setTaskStatus("训练完成");
                taskMapper.updateTaskStatus(task.getId(),"训练完成");
                System.out.println("Thread is finished");
                System.out.println("-----------------------------------------");
                break;
            }
            if(status.equals("Running")) {
                newline++;
                if (newline++%60==0){
                    newline=0;
                    System.out.println("\n");
                }
                System.out.print("#");
                queryTaskMetrics(task);
            }
            if(status.equals("Failed")) {
                taskMapper.updateTaskStatus(task.getId(),"训练失败");
                System.out.println("\ntrain task failed");
                return ;
            }
        }
        return ;
    }
}
