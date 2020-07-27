package com.fl.server.controller.ServerAdmin;

import com.fl.server.object.tools.Message;
import com.fl.server.object.tools.TypeFactor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

// 平台管理-用户管理页
@RestController
@CrossOrigin(origins="*",maxAge = 3600)
public class userAdmin {
    //@Autowired
    //private UserMapper userMapper;

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

        try {
            // 处理数据库逻辑
            if (askUserType.equals("") && askUserAccount.equals("")){

            }
            else if(!askUserType.equals("")){

            }
            else{

            }
            // boolean status = ReqNodes




            message.setState(true);
            message.setMessage("operation has been done");
        }catch (Exception e){
            System.out.println(e.toString());

            message.setState(false);
            message.setMessage("服务器运行异常");
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


            message.setState(true);
            message.setMessage("operation has been done");
        }catch (Exception e){
            System.out.println(e.toString());

            message.setState(false);
            message.setMessage("服务器运行异常");
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


            message.setState(true);
            message.setMessage("operation has been done");
        }catch (Exception e){
            System.out.println(e.toString());

            message.setState(false);
            message.setMessage("服务器运行异常");
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


            message.setState(true);
            message.setMessage("operation has been done");
        }catch (Exception e){
            System.out.println(e.toString());

            message.setState(false);
            message.setMessage("服务器运行异常");
        }
        finally {
            output.put("message", message);
        }
        return output;
    }
}