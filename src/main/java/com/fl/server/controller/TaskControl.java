package com.fl.server.controller;

import com.fl.server.mapper.TaskMapper;
import com.fl.server.mapper.UserMapper;
import com.fl.server.object.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class TaskControl {
    @Autowired
    private TaskMapper taskMapper;

    @PostMapping("/launchTask")
    @ResponseBody
    public HashMap<String, Object> TaskLaunch(
            @RequestParam("email") String email,
            @RequestParam("describe") String describe
    ) {
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
    )  {
        // add to the db
        StringBuilder selectsString = new StringBuilder();
        for (String select : selects) {
            selectsString.append(select).append("/");
        }

        System.out.println(selectsString);

        Boolean status = taskMapper.addSelectedFeaturesToTask(taskID, selectsString.toString());

        // return message
        Message message = new Message();
        message.setState(status);
        message.setMessage(status? "attr filter successfully" : "something is wrong here");

        return message;
    }


}