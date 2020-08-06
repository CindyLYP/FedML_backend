package com.fl.server.communication;

import org.json.JSONObject;
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

    @Async
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
