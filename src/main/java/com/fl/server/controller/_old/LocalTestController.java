package com.fl.server.controller._old;


import com.fl.server.mapper.NodeMapper;
import com.fl.server.object.old.Feature;
import com.fl.server.object.old.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
@CrossOrigin(origins="*",maxAge = 3600)
public class LocalTestController {

    @Autowired
    private NodeMapper nodeMapper;

    @CrossOrigin
    @PostMapping(value = "api/login")
    @ResponseBody
    public int login(@RequestBody User ResponseUser) {
        String name = ResponseUser.getUsername();
        String pwd = ResponseUser.getPassword();
        System.out.println("receive json from frontend");
        return 1;
    }
}
