package com.fl.server.controller;

import com.fl.server.object.MD5;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.fl.server.object.User;
import com.fl.server.object.Message;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@RestController
public class Login{
    @GetMapping("/login")
    @ResponseBody
    public Message LoginCheck(@RequestBody User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Message message = new Message();

        // get the hash password
        String hashPassword = MD5.EncoderByMd5(user.getPassword());

        // check the information
        // status = DB_User.check(user.email, hashPassword)
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