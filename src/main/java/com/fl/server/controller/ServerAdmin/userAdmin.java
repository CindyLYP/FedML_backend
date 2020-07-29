package com.fl.server.controller.ServerAdmin;

import com.fl.server.mapper.NodeMapper;
import com.fl.server.mapper.UserMapper;
import com.fl.server.object.tools.Message;
import com.fl.server.object.tools.TypeFactor;
import com.fl.server.pojo.Node;
import com.fl.server.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

// 平台管理-用户管理页
@RestController
@CrossOrigin(origins="*",maxAge = 3600)
public class userAdmin {
    @Autowired
    private UserMapper userMapper;

    // 查询用户
    @PostMapping("/userReq")
    @ResponseBody
    public HashMap<String, Object> NodeReq(
            @RequestParam("askUserType") String askUserType,
            @RequestParam("askUserAccount") String askUserAccount,

            @RequestParam("operator") String operator
    ) {
        // 填充结果
        HashMap<String, Object> output = TypeFactor.GenerateHMSO();
        Message message = new Message();

        ArrayList<User> reqUsers;
        try {
            /* 处理数据库逻辑 */
            if ("".equals(askUserType) && "".equals(askUserAccount)){
                reqUsers = userMapper.getAllUser();
            }
            else if(! "".equals(askUserType)){
                reqUsers = userMapper.selectByAccount(askUserType);
            }
            else{
                reqUsers = userMapper.selectByAccount(askUserAccount);
            }

            int usersNum = reqUsers.size();
            output.put("usersNum", usersNum);

            ArrayList<Object> users = TypeFactor.GenerateALO();
            for(User reqUser: reqUsers){
                HashMap<String, Object> user = TypeFactor.GenerateHMSO();
                user.put("userName", reqUser.getUsername());
                user.put("userAccount", reqUser.getUserAccount());
                user.put("userType", reqUser.getUserType());
                user.put("institution", reqUser.getInstitution());

                users.add(user);
            }
            output.put("users", users);
            message.set(true, "用户查询成功");
        }catch (Exception e){
            System.out.println(e.toString());
            message.set(false, "服务器运行异常");
        }finally {
            output.put("message", message);
        }
        return output;
    }


    // 创建用户
    @PostMapping("/userCreate")
    @ResponseBody
    public HashMap<String, Object> NodeCreate(
            @RequestParam("userName") String userName,
            @RequestParam("userAccount") String userAccount,
            @RequestParam("password") String password,
            @RequestParam("userType") String userType,
            @RequestParam("institution") String institution,

            @RequestParam("operator") String operator
    ) {
        // 填充结果
        HashMap<String, Object> output = TypeFactor.GenerateHMSO();
        Message message = new Message();

        try {
            ArrayList<User> reqUsers = userMapper.selectByAccount(userAccount);
            if (reqUsers.size() == 0){
                User user = new User(userAccount, userName, password, userType, institution, true);
                if (! userMapper.insert(user)){
                    throw new Exception("抛出异常");
                }
            }else{
                message.set(false, "用户账户已存在");
            }
            message.set(true, "用户创建成功");
        }catch (Exception e){
            System.out.println(e.toString());
            message.set(false, "服务器运行异常");
        }
        finally {
            output.put("message", message);
        }
        return output;
    }


    // 修改用户
    @PostMapping("/userModify")
    @ResponseBody
    public HashMap<String, Object> NodeModify(
            @RequestParam("userName") String userName,
            @RequestParam("userAccount") String userAccount,
            @RequestParam("password") String password,
            @RequestParam("userType") String userType,
            @RequestParam("institution") String institution,

            @RequestParam("operator") String operator
    ) {
        // 填充结果
        HashMap<String, Object> output = TypeFactor.GenerateHMSO();
        Message message = new Message();

        try {
            User user = new User(userAccount, userName, password, userType, institution, true);
            if (! userMapper.update(user)){
                throw new Exception("抛出异常");
            }
            message.set(true, "用户修改成功");

        }catch (Exception e){
            System.out.println(e.toString());
            message.set(false, "服务器运行异常");
        }
        finally {
            output.put("message", message);
        }
        return output;
    }

    // 删除用户
    @PostMapping("/userDelete")
    @ResponseBody
    public HashMap<String, Object> NodeDelete(
            @RequestParam("userAccount") String userAccount,

            @RequestParam("operator") String operator
    ) {
        // 填充结果
        HashMap<String, Object> output = TypeFactor.GenerateHMSO();
        Message message = new Message();

        try {
            if (! userMapper.delete(userAccount)){
                throw new Exception("抛出异常");
            }
            message.set(true, "用户删除成功");
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