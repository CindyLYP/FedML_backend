package com.fl.server.controller._old;

import com.fl.server.mapper.DataSourceMapper;
import com.fl.server.mapper.TaskMapper;
import com.fl.server.object.old.Client;
import com.fl.server.object.old.Feature;
import com.fl.server.object.old.Message;
import com.fl.server.object.old.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins="*",maxAge = 3600)
public class TaskControl {
    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private DataSourceMapper dataSourceMapper;

    @PostMapping("/dataSourceReq")
    @ResponseBody
    public HashMap<String, Object> DataSourceReq(
    ) {
        // add to the db
        ArrayList<Client> clientsList = dataSourceMapper.selectAllClients();

        // generate the req
        ArrayList<String> resClientsList = new ArrayList<String>();
        for(Client client:clientsList){
            resClientsList.add(client.getClientName());
        }

        // return message
        Boolean status = true;
        Message message = new Message();
        message.setState(status);
        message.setMessage(status? "data source request successfully" : "something is wrong here");

        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("message", message);
        hashMap.put("clients", resClientsList);
        return hashMap;
    }


    @PostMapping("/dataFeatReq")
    @ResponseBody
    public HashMap<String, Object> DataFeatsReq(
        @RequestParam("clientsList")  ArrayList<String> clientsList
    ) {
        // add to the db
        ArrayList<Feature> featuresList = dataSourceMapper.selectAllFeatures();


        // generate the req
        ArrayList<Feature> resFeaturesList = new ArrayList<Feature>();

        for(String client:clientsList){
            for(Feature feature:featuresList){
                if(client.equals(feature.getOwner())){
                    resFeaturesList.add(feature);
                }
            }
        }

        // return message
        Boolean status = true;
        Message message = new Message();
        message.setState(status);
        message.setMessage(status? "data features request successfully" : "something is wrong here");

        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("message", message);
        hashMap.put("resFeaturesList", resFeaturesList);
        return hashMap;
    }



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
        System.out.println(status);
        // return message
        Message message = new Message();
        message.setState(status);
        message.setMessage(status? "attr filter successfully" : "something is wrong here");

        return message;
    }


}
