package com.fl.server.controller._old;

import com.fl.server.mapper.old.UserMapper;
import com.fl.server.object.old.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fl.server.object.old.User;
import com.fl.server.object.tools.Message;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;

@RestController
@CrossOrigin(origins="*",maxAge = 3600)
public class UserControl {
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/loginReq")
    @ResponseBody
    public HashMap<String, Object> UserLogin(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Message message = new Message();
        // get the hash password
        String hashPassword = MD5.EncoderByMd5(password);

        // get the usr information
        User user = userMapper.selectByEmail(username);
        // return message
        if (user == null){
            message.setState(false);
            message.setMessage("user does not exist!");
        }
        else if (!user.getPassword().equals(hashPassword)){
            message.setState(false);
            message.setMessage("password is wrong!");
        }
        else {
            message.setState(true);
            message.setMessage("user login successfully!");
        }
        String token = MD5.EncoderByMd5((new Date()).toString());

        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("message", message);
        hashMap.put("token", token);
        return hashMap;
    }


    @PostMapping("/userReg")
    @ResponseBody
    public Message UserRegisterCheck(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("company") String company,
            @RequestParam("classify") String classify
    ) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        User user = new User();
        user.setPassword(MD5.EncoderByMd5(password));
        user.setEmail(username);
        user.setUsername(username);
        user.setCompany(company);

        // add to the db
        Boolean status = userMapper.insert(user);

        // return message
        Message message = new Message();
        message.setState(status);
        message.setMessage(status? "register successfully" : "something is wrong here");
        return message;
    }
}

