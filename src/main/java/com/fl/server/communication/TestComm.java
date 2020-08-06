package com.fl.server.communication;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
@Service
public class TestComm {
    public String send(){
        String url = "http://localhost:8888/testComm?name={name}&pwd={pwd}";

        RestTemplate restTemplate = new RestTemplate();
        HashMap<String,String> d=new HashMap<>();
        d.put("name","ysc");
        d.put("pwd","1234");
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class,d);
        return response.getBody();
    }
    @Async
    public void send2() throws InterruptedException {
        int i=0;
        while(i<10){
            Thread.currentThread().sleep(1000);
            System.out.println(i++);
        }
    }
}
