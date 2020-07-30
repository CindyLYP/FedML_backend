package com.fl.server;

import com.fl.server.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class test {
    @Autowired
    private UserMapper userMapper;




}
