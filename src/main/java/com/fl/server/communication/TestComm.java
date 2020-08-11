package com.fl.server.communication;

import com.fl.server.mapper.UserMapper;
import com.fl.server.mapper.UtilsMapper;
import com.fl.server.pojo.Logger;
import com.fl.server.pojo.User;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
@Service
public class TestComm {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UtilsMapper utilsMapper;

    public String send() throws JSONException {
        String url = "http://localhost:8888/testComm";
        JSONObject jd = new JSONObject();
        jd.put("name","cindy");
        jd.put("pwd",123);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(jd.toString(), headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url,request, String.class);
        return response.getBody();
    }
    @Async
    public void send2(String name) throws InterruptedException {
        int i=0;
        while(i<4){
            Thread.currentThread().sleep(1000);
            User user = userMapper.selectByAccount(name).get(0);
            System.out.println(user.getUserType());
            System.out.println(i++);
        }
        Logger log =new Logger();
        log.setUserId(1);
        log.setInfo("just for test");
        utilsMapper.addLog(log);
        System.out.println("add log finish");
    }
}
