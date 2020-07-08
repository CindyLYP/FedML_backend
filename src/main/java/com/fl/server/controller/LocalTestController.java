package com.fl.server.controller;


import com.fl.server.mapper.TaskMapper;
import com.fl.server.mapper.UserMapper;
import com.fl.server.object.Dataset;
import com.fl.server.object.Task;
import com.fl.server.object.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class LocalTestController {

    @Autowired
     private UserMapper userMapper;

    @Autowired
    private TaskMapper taskMapper;

    @CrossOrigin
    @PostMapping(value = "api/login")
    @ResponseBody
    public int login(@RequestBody User ResponseUser) {
        String name = ResponseUser.getUsername();
        String pwd = ResponseUser.getPassword();
        System.out.println("receive json from frontend");

        ResponseUser.setCompany("jztb");
        ResponseUser.setEmail("ysc@xjtu.edu.cn");
        Task task = new Task();
        task.setEmail("hello");
        task.setTaskId("2001");
        task.setDesc("hello world");
        taskMapper.createTaskByEmail(task);
        ArrayList<Dataset> d = taskMapper.selectDatasetByTask("1003");
        for (int i=0;i<d.size();i++)
        System.out.println(d.get(i).getDatasetId());
        return 1;
    }
}
