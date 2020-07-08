package com.fl.server.controller;

import com.fl.server.mapper.TaskMapper;
import com.fl.server.mapper.UserMapper;
import com.fl.server.object.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TaskControl {
    @Autowired
    private TaskMapper taskMapper;

    @PostMapping("/launchTask")
    @ResponseBody
    public HashMap<String, Object> TaskLaunch(
            @RequestParam("email") String email,
            @RequestParam("describe") String describe
    ) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Task task = new Task();
        task.setTaskId("T_" + email + "_"  + (new Date()).toString());
        task.setDesc(describe);
        task.setEmail(email);

        // add to the db
        Boolean status = taskMapper.createTaskByEmail(task);

        // return message
        Message message = new Message();
        message.setState(status);
        message.setMessage(status? "launch task successfully" : "something is wrong here");

        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("message", message);
        hashMap.put("taskID", task.getTaskId());
        return hashMap;
    }

    @PostMapping("/attrFilter")
    @ResponseBody
    public Message AttrFilter(
            @RequestParam("taskID") String taskID,
            @RequestParam("selects") ArrayList<String> selects
    ) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        // add to the db
        StringBuilder selectsString = new StringBuilder();
        for (String select : selects) {
            selectsString.append(select);
        }

        System.out.println(selectsString);
//        Dataset dataset = new Dataset();
//        dataset.setFeatures(selectsString);
//        dataset.setTaskId(taskID);
//        dataset.setTaskId("D_" + taskID + "_" + (new Date()).toString());
//
//        Boolean status = taskMapper.createDatasetByTask(dataset);
        boolean status = true;

        // return message
        Message message = new Message();
        message.setState(status);
        message.setMessage(status? "attr filter successfully" : "something is wrong here");

        return message;
    }


    @PostMapping("/datasetChoice")
    @ResponseBody
    public HashMap<String, Object> DatasetChoice(
            @RequestParam("rules") ArrayList<String> rules,
            @RequestParam("frac") Float frac
    ) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        StringBuilder selectsString = new StringBuilder();
        for (String rule : rules) {
            rule.split(' and ');
//
//            System.out.println(rule.get(0));
//            System.out.println(rule.get(1));
//            System.out.println(rule.get(2));
        }


        boolean status = true;
        // return message
        Message message = new Message();
        message.setState(status);
        message.setMessage(status? "dataset choice successfully" : "something is wrong here");

        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("message", message);
        hashMap.put("datasetID", -1);
        return hashMap;

    }
}
