package com.fl.server.controller._old;


import com.fl.server.communication.TaskComm;
import com.fl.server.communication.TestComm;
import com.fl.server.mapper.DatasetMapper;
import com.fl.server.mapper.NodeMapper;
import com.fl.server.mapper.UtilsMapper;
import com.fl.server.object.old.Feature;
import com.fl.server.object.old.User;

import com.fl.server.pojo.Dataset;
import org.assertj.core.internal.IterableElementComparisonStrategy;
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
    private DatasetMapper datasetMapper;

    @CrossOrigin
    @PostMapping(value = "api/login")
    @ResponseBody
    public int login(@RequestBody User ResponseUser) throws JSONException, InterruptedException {
        String name = ResponseUser.getUsername();
        String pwd = ResponseUser.getPassword();
        System.out.println("receive json from frontend");

        Dataset ad = datasetMapper.selectByUserId(21).get(0);
        System.out.println(ad.getDatasetName());
        ad.setAlignedNum(999);
        datasetMapper.update(ad);
        return 1;
    }

    @CrossOrigin
    @GetMapping(value = "/testComm")
    @ResponseBody
    public String test(@RequestParam("name")String name,@RequestParam("pwd") String pwd){
        System.out.println("backend receive json from server");
        System.out.println(name);
        return "to server";
    }

}
