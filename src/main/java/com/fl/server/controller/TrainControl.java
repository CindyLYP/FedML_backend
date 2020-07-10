package com.fl.server.controller;

import com.fl.server.mapper.TaskMapper;
import com.fl.server.object.Message;
import com.fl.server.object.Task;
import com.fl.server.object.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class TrainControl {
    @Autowired
    private TaskMapper taskMapper;

    @PostMapping("/trainLaunch")
    @ResponseBody
    public HashMap<String, Object> TrainLaunch(
        @RequestParam("datasetID") String datasetID,
        @RequestParam("modelName") String modelName,
        @RequestParam("parameters") String parameters
        ) {
        Train train = new Train();




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

    @PostMapping("/trainDelete")
    @ResponseBody
    public Message TrainDelete(
        @RequestParam("trainID") String trainID
    ) {
        boolean status = taskMapper.deleteTrain(trainID);

        // return message
        Message message = new Message();
        message.setState(status);
        message.setMessage(status? "train delete successfully" : "something is wrong here");

        return message;
    }

}
