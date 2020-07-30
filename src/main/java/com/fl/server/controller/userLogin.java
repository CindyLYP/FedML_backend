package com.fl.server.controller;

import com.fl.server.mapper.UserMapper;
import com.fl.server.object.old.MD5;
import com.fl.server.object.tools.Message;
import com.fl.server.object.tools.TypeFactory;
import com.fl.server.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@RestController
public class userLogin {
    @Autowired
    private UserMapper userMapper;

    // 创建用户
    @PostMapping("/userLogin")
    @ResponseBody
    public HashMap<String, Object> UserLogin(
            @RequestParam("operator") String operator,
            @RequestParam("password") String password
    ) {

        // 填充结果
        HashMap<String, Object> output = TypeFactory.GenerateHMSO();
        Message message = new Message();

        try {
            ArrayList<User> reqUsers = userMapper.selectByAccount(operator);

            if (reqUsers.size() == 0){
                message.set(false, "用户账户不存在");
            } else if(! reqUsers.get(0).getPassword().equals(password)){
                message.set(false, "用户密码不正确");
            }else{
                output.put("userName", reqUsers.get(0).getUsername());
                output.put("userType",reqUsers.get(0).getUserType());
                output.put("token", MD5.EncoderByMd5((new Date()).toString()));
                message.set(true, "登录成功");
            }
        }catch (Exception e){
            System.out.println(e.toString());
            message.set(false, "服务器运行异常");
        }
        finally {
            output.put("message", message);
        }
        return output;
    }

}
