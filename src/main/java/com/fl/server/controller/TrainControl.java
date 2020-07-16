package com.fl.server.controller;

import com.fl.server.mapper.TaskMapper;
import com.fl.server.object.Message;
import com.fl.server.object.Task;
import com.fl.server.object.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@RestController
@CrossOrigin(origins="*",maxAge = 3600)
public class TrainControl {
    @Autowired
    private TaskMapper taskMapper;

    @PostMapping("/trainLaunch")
    @ResponseBody
    public HashMap<String, Object> TrainLaunch(
        @RequestParam("taskID") String taskID,
        @RequestParam("datasetID") String datasetID,
        @RequestParam("modelName") String modelName,
        @RequestParam("parameters") String parameters
        ) {
//        Train train = new Train();
//        train.setModelType(modelName);
//        train.setParamDim(parameters);
//        train.setTrainId("test_"+taskID);
//        train.setTaskId(taskID);
//
//        // add to the db
//        Boolean status = taskMapper.createTrainByTask(train);
        Boolean status = true;

        // return message
        Message message = new Message();
        message.setState(status);
        message.setMessage(status? "launch train successfully" : "something is wrong here");

        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("message", message);
//        hashMap.put("trainID", train.getTrainId());
        hashMap.put("trainID", "0713" + taskID);
        return hashMap;

    }

    @PostMapping("/trainDelete")
    @ResponseBody
    public Message TrainDelete(
        @RequestParam("trainID") String trainID
    ) {
//        boolean status = taskMapper.deleteTrain(trainID);
        boolean status = true;

        // return message
        Message message = new Message();
        message.setState(status);
        message.setMessage(status? "train delete successfully" : "something is wrong here");

        return message;
    }

    @PostMapping("/trainRestart")
    @ResponseBody
    public Message TrainRestart(
        @RequestParam("trainID") String trainID
    ) {


        boolean status = true;

        // return message
        Message message = new Message();
        message.setState(status);
        message.setMessage(status? "train delete successfully" : "something is wrong here");
        return message;
    }

    @PostMapping("/getTrainState")
    @ResponseBody
    public HashMap<String, Object> GetTrainState(
        @RequestParam("trainID") String trainID
    ) {
        Boolean status = true;

        // return message
        Message message = new Message();
        message.setState(status);
        message.setMessage(status? "get train state successfully" : "something is wrong here");

        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("message", message);
        hashMap.put("state", "interface works");
        return hashMap;
    }

    @PostMapping("/getTrainBatchInfo")
    @ResponseBody
    public HashMap<String, Object> GetTrainBatchInfo(
            @RequestParam("trainID") String trainID
    ) {
        Boolean status = true;

        // return message
        Message message = new Message();
        message.setState(status);
        message.setMessage(status? "get train batch info successfully" : "something is wrong here");

        ArrayList<Double> recent_loss = new ArrayList<Double>();
        recent_loss.add(0.11);
        recent_loss.add(0.12);
        recent_loss.add(0.13);

        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("message", message);
        hashMap.put("n_rounds", 10);
        hashMap.put("recent_loss", recent_loss);

        ArrayList<HashMap<String, Double>> recent_metrics = new ArrayList<HashMap<String, Double>>();
        for(Double rl:recent_loss){
            HashMap<String, Double> recent_metric = new HashMap<String, Double>();
            recent_metric.put("auc", 0.5);
            recent_metric.put("ks", 0.2);
            recent_metrics.add(recent_metric);
        }

        hashMap.put("recent_metrics", recent_metrics);

        return hashMap;
    }
}
