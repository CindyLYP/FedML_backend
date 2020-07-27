package com.fl.server.controller.ServerAdmin;

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
            @RequestParam("operator") String operator,
            @RequestParam("token") String token
    ) {
        // 处理数据库逻辑
        // boolean status = ReqNodes

        // 填充结果
        HashMap<String, Object> output = TypeFactor.GenerateHMSO();

        int nodesNum = 10;
        output.put("nodesNum", nodesNum);

        ArrayList<Object> nodes = TypeFactor.GenerateALO();
        for(int i = 0; i < nodesNum; i+=1){
            HashMap<String, Object> node = TypeFactor.GenerateHMSO();

        }

        return output;
    }

    @PostMapping("/tpfunc")
    @ResponseBody
    public HashMap<String, Object> Tpfunc(
            @RequestParam("para1") String para1,
            @RequestParam("para2") String para2
    ) {



        // 填充结果
        HashMap<String, Object> output = TypeFactor.GenerateHMSO();
        return output;
    }



}