package com.fl.server.controller._old;

import com.fl.server.mapper.TaskMapper;
import com.fl.server.object.old.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin(origins="*",maxAge = 3600)
public class DatasetControl {
    @Autowired
    private TaskMapper taskMapper;

    @PostMapping("/datasetChoice")
    @ResponseBody
    public HashMap<String, Object> DatasetChoice(
            @RequestParam("taskID") String taskID,
            @RequestParam("rules") String rules,
//            @RequestParam("rules") String rules,
            @RequestParam("frac") Float frac
    ) {
        System.out.println(rules.toString());

        StringBuilder selectsString = new StringBuilder();
//        for (String rule : rules) {
//            String[] result = rule.split(" and ");
//            System.out.println(Arrays.toString(result));
//        }
//
//        // construct the dataset object
//        Dataset dataset = new Dataset();
//        dataset.setTaskId(taskID);
//        dataset.setDatasetId("D_" + taskID + "_"  + (new Date()).toString());
//        dataset.setFilteringRules(rules.toString());

//        boolean status = taskMapper.createDatasetByTask(dataset);
        boolean status = true;

        Message message = new Message();
        message.setState(status);
        message.setMessage(status? "dataset choice successfully" : "something is wrong here");
//
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("message", message);
        hashMap.put("datasetID", 1231);
        return hashMap;

//{"0":{"name":"存款余额", "symbol":"＞", "value":"1500", "key":1594360527161}}
    }

    @PostMapping("/datasetDel")
    @ResponseBody
    public Message DatasetDel(
            @RequestParam("datasetID") String datasetID
    ) {
//        boolean status = taskMapper.deleteDataset(datasetID);
        System.out.println(datasetID);
        boolean status = true;

        // return message
        Message message = new Message();
        message.setState(status);
        message.setMessage(status? "dataset delete successfully" : "something is wrong here");

        return message;
    }

}
