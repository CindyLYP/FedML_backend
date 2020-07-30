package com.fl.server.controller.Client;

import com.fl.server.object.tools.Message;
import com.fl.server.object.tools.TypeFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;


// 普通用户-数据管理页
@RestController
@CrossOrigin(origins="*",maxAge = 3600)
public class DataSetAdmin {
    //@Autowired
    //private UserMapper userMapper;

    // 查询数据集
    @PostMapping("/datasetReq")
    @ResponseBody
    public HashMap<String, Object> DatasetReq(
            @RequestParam("scene") String scene,

            @RequestParam("operator") String operator
    ) {
        // 填充结果
        HashMap<String, Object> output = TypeFactory.GenerateHMSO();
        Message message = new Message();


        try {
            // 处理数据库逻辑
            // boolean status = ReqNodes


            message.setState(true);
            message.setMessage("数据集查询成功");
        }catch (Exception e){
            System.out.println(e.toString());

            message.setState(false);
            message.setMessage("服务器运行异常");
        }finally {
            output.put("message", message);
        }
        return output;
    }

    // 删除数据集
    @PostMapping("/datasetDelete")
    @ResponseBody
    public HashMap<String, Object> SceneDelete(
            @RequestParam("scene") String scene,

            @RequestParam("operator") String operator
    ) {
        // 填充结果
        HashMap<String, Object> output = TypeFactory.GenerateHMSO();
        Message message = new Message();


        try {


            message.setState(true);
            message.setMessage("数据集删除成功");
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

    // 生成数据集
    @PostMapping("/datasetGen")
    @ResponseBody
    public HashMap<String, Object> DatasetGen(
            @RequestParam("scene") String scene,
            @RequestParam("datasetName") String datasetName,
            @RequestParam("dict") ArrayList<HashMap<String, Object>> dict,

            @RequestParam("operator") String operator
    ) {
        // 填充结果
        HashMap<String, Object> output = TypeFactory.GenerateHMSO();
        Message message = new Message();


        try {


            message.setState(true);
            message.setMessage("数据集生成成功");
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