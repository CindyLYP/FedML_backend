package com.fl.server.controller.ServerAdmin;

import com.fl.server.object.NodeRef.NodeInfo;
import com.fl.server.object.tools.Message;
import com.fl.server.object.tools.TypeFactor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;


// 平台管理-节点管理页
@RestController
@CrossOrigin(origins="*",maxAge = 3600)
public class nodeAdmin {
    //@Autowired
    //private UserMapper userMapper;

    // 节点查询
    @PostMapping("/nodeReq")
    @ResponseBody
    public HashMap<String, Object> NodeReq(
            @RequestParam("operator") String operator
    ) {
        // 填充结果
        HashMap<String, Object> output = TypeFactor.GenerateHMSO();
        Message message = new Message();


        try {
            // 处理数据库逻辑
            // boolean status = ReqNodes

            int nodesNum = 10;
            output.put("nodesNum", nodesNum);

            ArrayList<Object> nodes = TypeFactor.GenerateALO();
            for(int i = 0; i < nodesNum; i+=1){
                HashMap<String, Object> node = TypeFactor.GenerateHMSO();

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


    // 创建节点
    @PostMapping("/nodeCreate")
    @ResponseBody
    public HashMap<String, Object> NodeCreate(
            @RequestParam("nodeName") String nodeName,
            @RequestParam("ipAddress") String ipAddress,
            @RequestParam("port") String port,
            @RequestParam("CSV_path") String CSV_path,
            @RequestParam("logo") String logo,

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


    // 修改节点
    @PostMapping("/nodeModify")
    @ResponseBody
    public HashMap<String, Object> NodeModify(
            @RequestParam("nodeName") String nodeName,
            @RequestParam("ipAddress") String ipAddress,
            @RequestParam("port") String port,
            @RequestParam("CSV_path") String CSV_path,
            @RequestParam("logo") String logo,

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

    // 删除节点
    @PostMapping("/nodeDelete")
    @ResponseBody
    public HashMap<String, Object> NodeDelete(
            @RequestParam("nodeName") String nodeName,

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