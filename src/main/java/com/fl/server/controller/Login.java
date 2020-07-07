package com.fl.server.controller;

import com.fl.server.mapper.UserMapper;
import com.fl.server.object.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fl.server.object.User;
import com.fl.server.object.Message;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@RestController
public class Login{
    @Autowired
    private UserMapper userMapper;
    @PostMapping("/login")
    @ResponseBody
    public Message LoginCheck(
            @RequestParam("email") String email,
            @RequestParam("password") String password
    ) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        Message message = new Message();
        System.out.println(email);
        System.out.println(password);

        // get the hash password
        //        String hashPassword = MD5.EncoderByMd5(user.getPassword());

        // check the information
        User user = userMapper.selectByEmail(email);


        // message.setState(false);

        // if (status == 0){
        message.setState(true);
        message.setMessage("user login successfully!");
        //}
        // else if (status == 1)
        //      message.setMessage("user does not exist!");

        // else if (status == 2)
        //      message.setMessage("password is wrong!");

        // else{message.setMessage("something wrong in there!");}
        return message;
    }
}