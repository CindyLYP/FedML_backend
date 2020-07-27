package com.fl.server.controller.NodeAdmin;

import com.fl.server.object.tools.Message;
import com.fl.server.object.tools.TypeFactor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

// 节点管理-场景设置页
@RestController
@CrossOrigin(origins="*",maxAge = 3600)
public class SenceAdmin {
    //@Autowired
    //private UserMapper userMapper;

    // 查询场景
    @PostMapping("/sceneReq")
    @ResponseBody
    public HashMap<String, Object> SceneReq(
            @RequestParam("specific") boolean specific,

            @RequestParam("operator") String operator
    ) {
        // 填充结果
        HashMap<String, Object> output = TypeFactor.GenerateHMSO();
        Message message = new Message();


        try {
            // 处理数据库逻辑
            // boolean status = ReqNodes
            if (specific){

            }else {

            }

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


    // 创建场景
    @PostMapping("/sceneCreate")
    @ResponseBody
    public HashMap<String, Object> NodeCreate(
            @RequestParam("sceneName") String sceneName,
            @RequestParam("target") String target,
            @RequestParam("describe") ArrayList<HashMap<String, String>> describe,

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


    // 修改场景
    @PostMapping("/sceneModify")
    @ResponseBody
    public HashMap<String, Object> SceneModify(
            @RequestParam("sceneName") String sceneName,
            @RequestParam("target") String target,
            @RequestParam("describe") ArrayList<HashMap<String, String>> describe,

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

    // 删除场景
    @PostMapping("/sceneDelete")
    @ResponseBody
    public HashMap<String, Object> SceneDelete(
            @RequestParam("sceneName") String sceneName,

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