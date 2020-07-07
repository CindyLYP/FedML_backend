package com.fl.server.controller;


import com.fl.server.mapper.UserMapper;
import com.fl.server.object.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LocalTestController {

    @Autowired
     private UserMapper userMapper;

    @CrossOrigin
    @PostMapping(value = "api/login")
    @ResponseBody
    public int login(@RequestBody User ResponseUser) {
        String name = ResponseUser.getUsername();
        String pwd = ResponseUser.getPassword();
        System.out.println("receive json from frontend");
        ResponseUser.setCompany("jztb");
        ResponseUser.setEmail("ysc@xjtu.edu.cn");
        userMapper.insert(ResponseUser);

        return 1;
    }
}
