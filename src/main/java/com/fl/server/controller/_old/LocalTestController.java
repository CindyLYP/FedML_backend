package com.fl.server.controller._old;


import com.fl.server.communication.TaskComm;
import com.fl.server.communication.TestComm;
import com.fl.server.mapper.DatasetMapper;
import com.fl.server.mapper.NodeMapper;
import com.fl.server.mapper.TaskMapper;
import com.fl.server.mapper.UtilsMapper;
import com.fl.server.object.old.Feature;
import com.fl.server.object.old.User;

import com.fl.server.pojo.Dataset;
import com.fl.server.pojo.Task;
import org.assertj.core.internal.IterableElementComparisonStrategy;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

@Controller
@CrossOrigin(origins="*",maxAge = 3600)
public class LocalTestController {

    @Autowired
    private UtilsMapper utilsMapper;
    @Autowired
    private TestComm testComm;
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private DatasetMapper datasetMapper;

    @CrossOrigin
    @PostMapping(value = "api/login")
    @ResponseBody
    public int login(@RequestBody User ResponseUser) throws JSONException, InterruptedException {
        String name = ResponseUser.getUsername();
        String pwd = ResponseUser.getPassword();
        System.out.println("receive json from frontend");
        HashMap res = new HashMap();
        Task task = taskMapper.selectByTaskName("v1").get(0);
        JSONArray js = new JSONArray(task.getParameters());
        JSONObject rd = (JSONObject) js.get(1);
        System.out.println(rd.get("学习率"));

        return 1;
    }

    @CrossOrigin
    @PostMapping(value = "/testComm")
    @ResponseBody
    public String test(@RequestBody HashMap x){
        System.out.println("backend receive json from server");
        System.out.println(x.toString());
        return "to server";
    }

}
