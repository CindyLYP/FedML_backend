package com.fl.server.controller._old;


import com.fl.server.communication.TaskComm;
import com.fl.server.mapper.NodeMapper;
import com.fl.server.mapper.UtilsMapper;
import com.fl.server.object.old.Feature;
import com.fl.server.object.old.User;

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
    public int login(@RequestBody User ResponseUser) {
        String name = ResponseUser.getUsername();
        String pwd = ResponseUser.getPassword();
        System.out.println("receive json from frontend");
        TaskComm test = new TaskComm();
        test.TestUtils();

        return 1;
    }

    @CrossOrigin
    @PostMapping(value = "/test")
    @ResponseBody
    public String test(@RequestBody HashMap<String ,Object> res){
        System.out.println("backend receive json from server");
        System.out.println(res.get("name")+":"+String.valueOf(res.get("age")));
        return "to server";
    }

}
