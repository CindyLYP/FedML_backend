package com.fl.server.controller;

import com.fl.server.mapper.UserMapper;
import com.fl.server.object.MD5;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fl.server.object.User;
import com.fl.server.object.Message;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
public class UserControl {
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/login")
    @ResponseBody
    public HashMap<String, Object> UserLogin(
            @RequestParam("email") String email,
            @RequestParam("password") String password
    ) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        Message message = new Message();
        // get the hash password
        String hashPassword = MD5.EncoderByMd5(password);

        // get the usr information
        User user = userMapper.selectByEmail(email);
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
        hashMap.put("data", message);
        hashMap.put("token", token);
        return hashMap;
    }


    @PostMapping("/register")
    @ResponseBody
    public Message UserRegisterCheck(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("company") String company
    ) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        User user = new User();
        user.setPassword(MD5.EncoderByMd5(password));
        user.setEmail(email);
        user.setUsername(email);
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
//    @PostMapping("/GetTrainState")
//    @ResponseBody
//    public <----> GetTrainState(
//            @RequestParam("trainID") String email,
//            ) throws UnsupportedEncodingException, NoSuchAlgorithmException {
//    }
//    @PostMapping("/GetTrainStatus")
//    @ResponseBody
//    public <----> GetTrainState(
//            @RequestParam("trainID") String email,
//            ) throws UnsupportedEncodingException, NoSuchAlgorithmException {
//    }
//
//
//    @PostMapping("/deleteTrain")
//    @ResponseBody
//    public <state> DeleteTrain(
//            @RequestParam("trainID") String email,
//            ) throws UnsupportedEncodingException, NoSuchAlgorithmException {
//    }
//
//    @PostMapping("/restartTrain")
//    @ResponseBody
//    public <state> RestartTrain(
//            @RequestParam("trainID") String email,
//            ) throws UnsupportedEncodingException, NoSuchAlgorithmException {
//    }
//
//    @PostMapping("/launchTrain")
//    @ResponseBody
//    public <trainID, state> LaunchTrain(
//            @RequestParam("datasetID") String email,
//            @RequestParam("modelName") String email,
//            @RequestParam("parameters") String email,
//            ) throws UnsupportedEncodingException, NoSuchAlgorithmException {
//    }
//
//    @PostMapping("/datasetDel")
//    @ResponseBody
//    public <state> DatasetDEL(
//            @RequestParam("datasetID") String email,
//    ) throws UnsupportedEncodingException, NoSuchAlgorithmException {
//    }
//
//    @PostMapping("/datasetChoice")
//    @ResponseBody
//    public <datasetID, state> DatasetChoice(
//            @RequestParam("rules") String email,
//            @RequestParam("frac") Float password
//    ) throws UnsupportedEncodingException, NoSuchAlgorithmException {
//    }
//
//
//
//    @PostMapping("/datasetChoice")
//    @ResponseBody
//    public <datasetID, state> DatasetChoice(
//            @RequestParam("rules") String email,
//            @RequestParam("frac") Float password
//    ) throws UnsupportedEncodingException, NoSuchAlgorithmException {
//    }
//
//    @PostMapping("/attrFilter")
//    @ResponseBody
//    public <state> AttrFilter(
//            @RequestParam("taskID") String email,
//            @RequestParam("selects") <List<String>> password
//    ) throws UnsupportedEncodingException, NoSuchAlgorithmException {
//    }
//
