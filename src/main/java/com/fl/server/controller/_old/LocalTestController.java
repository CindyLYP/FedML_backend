package com.fl.server.controller._old;


import com.fl.server.communication.TaskComm;
import com.fl.server.communication.TestComm;
import com.fl.server.mapper.NodeMapper;
import com.fl.server.mapper.UtilsMapper;
import com.fl.server.object.old.Feature;
import com.fl.server.object.old.User;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
@CrossOrigin(origins="*",maxAge = 3600)
public class LocalTestController {

    @Autowired
    private UtilsMapper utilsMapper;

    @CrossOrigin
    @PostMapping(value = "api/login")
    @ResponseBody
    public int login(@RequestBody User ResponseUser) throws JSONException {
        String name = ResponseUser.getUsername();
        String pwd = ResponseUser.getPassword();
        System.out.println("receive json from frontend");
        TestComm test = new TestComm();
        test.send();

        HashMap<String,Object> mp=new HashMap<>();
        HashMap<String,Integer> ts=new HashMap<>();
        ts.put("age",25);
        ts.put("study",18);
        mp.put("name","ysc");
        mp.put("num",ts);
        JSONObject js = new JSONObject(mp);
        String s = js.toString();
        JSONObject tmp = new JSONObject(s);
        System.out.println(tmp.get("num"));
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
