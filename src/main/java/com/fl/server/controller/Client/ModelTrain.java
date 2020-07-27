package com.fl.server.controller.Client;

import com.fl.server.object.tools.Message;
import com.fl.server.object.tools.TypeFactor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;


// 普通用户-模型训练页
public class ModelTrain {

    // 任务查询
    @PostMapping("/taskReq")
    @ResponseBody
    public HashMap<String, Object> TaskReq(
            @RequestParam("scene") String scene,

            @RequestParam("operator") String operator
    ) {
        // 填充结果
        HashMap<String, Object> output = TypeFactor.GenerateHMSO();
        Message message = new Message();


        try {


            message.setState(true);
            message.setMessage("任务查询成功");
        }catch (Exception e){
            System.out.println(e.toString());

            message.setMessage("服务器运行异常");
        }finally {
            output.put("message", message);
        }
        return output;
    }


    // 发起训练
    @PostMapping("/taskLaunch")
    @ResponseBody
    public HashMap<String, Object> TaskLaunch(
            @RequestParam("scene") String scene,
            @RequestParam("taskName") String taskName,
            @RequestParam("datasetName") String datasetName,
            @RequestParam("modelName") String modelName,
            @RequestParam("paramters") String paramters,

            @RequestParam("operator") String operator
    ) {
        // 填充结果
        HashMap<String, Object> output = TypeFactor.GenerateHMSO();
        Message message = new Message();


        try {


            message.setState(true);
            message.setMessage("任务发起成功");
        }catch (Exception e){
            System.out.println(e.toString());

            message.setMessage("服务器运行异常");
        }finally {
            output.put("message", message);
        }
        return output;
    }

    // 删除训练
    @PostMapping("/taskDelete")
    @ResponseBody
    public HashMap<String, Object> TaskDelete(
            @RequestParam("scene") String scene,
            @RequestParam("taskName") String taskName,

            @RequestParam("operator") String operator
    ) {
        // 填充结果
        HashMap<String, Object> output = TypeFactor.GenerateHMSO();
        Message message = new Message();


        try {


            message.setState(true);
            message.setMessage("任务发起成功");
        }catch (Exception e){
            System.out.println(e.toString());

            message.setMessage("服务器运行异常");
        }finally {
            output.put("message", message);
        }
        return output;
    }

    // 重新训练
    @PostMapping("/taskRestart")
    @ResponseBody
    public HashMap<String, Object> TaskRestart(
            @RequestParam("scene") String scene,
            @RequestParam("taskName") String taskName,

            @RequestParam("operator") String operator
    ) {
        // 填充结果
        HashMap<String, Object> output = TypeFactor.GenerateHMSO();
        Message message = new Message();


        try {


            message.setState(true);
            message.setMessage("训练重启成功");
        }catch (Exception e){
            System.out.println(e.toString());

            message.setMessage("服务器运行异常");
        }finally {
            output.put("message", message);
        }
        return output;
    }


    // 训练过程 - 训练状态接口
    @PostMapping("/taskStateReq")
    @ResponseBody
    public HashMap<String, Object> TaskStateReq(
            @RequestParam("scene") String scene,
            @RequestParam("taskName") String taskName,

            @RequestParam("operator") String operator
    ) {
        // 填充结果
        HashMap<String, Object> output = TypeFactor.GenerateHMSO();
        Message message = new Message();


        try {


            message.setState(true);
            message.setMessage("训练状态查询成功");
        }catch (Exception e){
            System.out.println(e.toString());

            message.setMessage("服务器运行异常");
        }finally {
            output.put("message", message);
        }
        return output;
    }


    // 训练过程 - 训练信息接口
    @PostMapping("/taskInfoReq")
    @ResponseBody
    public HashMap<String, Object> TaskInfoReq(
            @RequestParam("scene") String scene,
            @RequestParam("taskName") String taskName,

            @RequestParam("operator") String operator
    ) {
        // 填充结果
        HashMap<String, Object> output = TypeFactor.GenerateHMSO();
        Message message = new Message();


        try {


            message.setState(true);
            message.setMessage("训练信息查询成功");
        }catch (Exception e){
            System.out.println(e.toString());

            message.setMessage("服务器运行异常");
        }finally {
            output.put("message", message);
        }
        return output;
    }


    // 训练效果
    @PostMapping("/taskResReq")
    @ResponseBody
    public HashMap<String, Object> TaskResReq(
            @RequestParam("scene") String scene,
            @RequestParam("taskName") String taskName,

            @RequestParam("operator") String operator
    ) {
        // 填充结果
        HashMap<String, Object> output = TypeFactor.GenerateHMSO();
        Message message = new Message();


        try {


            message.setState(true);
            message.setMessage("训练效果查询成功");
        }catch (Exception e){
            System.out.println(e.toString());

            message.setMessage("服务器运行异常");
        }finally {
            output.put("message", message);
        }
        return output;
    }


}
