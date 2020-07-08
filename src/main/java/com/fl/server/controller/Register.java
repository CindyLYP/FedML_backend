package com.fl.server.controller;

import com.fl.server.object.MD5;
import com.fl.server.object.Message;
import com.fl.server.object.User;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@RestController
public class Register {
    @PostMapping("/register")
    @ResponseBody
    public Message RegisterCheck(@RequestBody User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Message message = new Message();
        String hashPassword = MD5.EncoderByMd5(user.getPassword());
        // add to the db
        // status = DB_User.add(user.email, hashPassword, user.company)
        // if (status == 0){
            message.setState(true);
            message.setMessage("test register func");
        //}

        return message;
    }
}


