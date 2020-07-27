package com.fl.server.controller.NodeAdmin;

import com.fl.server.object.tools.Message;
import com.fl.server.object.tools.TypeFactor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

// 节点管理-数据字典页
@RestController
@CrossOrigin(origins="*",maxAge = 3600)
public class DictAdmin {
    //@Autowired
    //private UserMapper userMapper;

    // 查询数据字典
    @PostMapping("/attributeReq")
    @ResponseBody
    public HashMap<String, Object> AttributeReq(
            @RequestParam("specific") boolean specific,

            @RequestParam("operator") String operator
    ) {
        // 填充结果
        HashMap<String, Object> output = TypeFactor.GenerateHMSO();
        Message message = new Message();


        try {
            // 处理数据库逻辑
            if (specific){

            }else {

            }

            message.setState(true);
            message.setMessage("operation has been done");
        } catch (Exception e) {
            System.out.println(e.toString());

            message.setState(false);
            message.setMessage("服务器运行异常");
        } finally {
            output.put("message", message);
        }
        return output;
    }

    // 更新数据字典
    @PostMapping("/attributeUpdate")
    @ResponseBody
    public HashMap<String, Object> AttributeUpdate(
            @RequestParam("csv_url") String csv_url,

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