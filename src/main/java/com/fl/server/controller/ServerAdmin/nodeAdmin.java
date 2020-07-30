package com.fl.server.controller.ServerAdmin;

import com.fl.server.mapper.NodeMapper;
import com.fl.server.object.tools.Message;
import com.fl.server.object.tools.TypeFactory;
import com.fl.server.pojo.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;


// 平台管理-节点管理页
@RestController
@CrossOrigin(origins="*",maxAge = 3600)
public class nodeAdmin {
    @Autowired
    private NodeMapper nodeMapper;

    // 节点查询
    @PostMapping("/nodeReq")
    @ResponseBody
    public HashMap<String, Object> NodeReq(
            @RequestParam("operator") String operator
    ) {
        System.out.println("----- NodeReq");
        // 填充结果
        HashMap<String, Object> output = TypeFactory.GenerateHMSO();
        Message message = new Message();

        try {
            // 处理数据库逻辑
            ArrayList<Node> reqNodes = nodeMapper.getAllNode();
            int nodesNum = reqNodes.size();
            output.put("nodesNum", nodesNum);
            ArrayList<Object> nodes = TypeFactory.GenerateALO();
            for(Node reqNode: reqNodes){
                HashMap<String, Object> node = TypeFactory.GenerateHMSO();
                node.put("nodeName", reqNode.getNodeName());
                node.put("ipAddress", reqNode.getIpAddress());
                node.put("port", reqNode.getPort());
                node.put("status", reqNode.getNodeStatus());
                node.put("CSV_path", reqNode.getCsvPath());
                node.put("logo", reqNode.getLogo());

                nodes.add(node);
            }
            output.put("nodes", nodes);
            message.set(true, "节点查询成功");
        }catch (Exception e){
            System.out.println(e.toString());
            message.set(false, "服务器运行异常");
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
        System.out.println("----- NodeCreate");
        // 填充结果
        HashMap<String, Object> output = TypeFactory.GenerateHMSO();
        Message message = new Message();

        try {
            // 查询是否名字已经存在
            ArrayList<Node> reqNodes = nodeMapper.findNode(nodeName);
            if (reqNodes.size() == 0){
                Node node = new Node(nodeName, ipAddress, port, CSV_path, logo, true);
                if (! nodeMapper.insert(node)){
                    throw new Exception("抛出异常");
                }

                message.set(true, "节点创建成功");
            }else{
                message.set(false, "节点名称已存在");
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


    // 修改节点
    @PostMapping("/nodeModify")
    @ResponseBody
    public HashMap<String, Object> NodeModify(
            @RequestParam("old_nodeName") String old_nodeName,
            @RequestParam("nodeName") String nodeName,
            @RequestParam("ipAddress") String ipAddress,
            @RequestParam("port") String port,
            @RequestParam("CSV_path") String CSV_path,
            @RequestParam("logo") String logo,

            @RequestParam("operator") String operator
    ) {
        System.out.println("----- NodeModify");
        // 填充结果
        HashMap<String, Object> output = TypeFactory.GenerateHMSO();
        Message message = new Message();

        try {
            // 查询是否名字已经存在
            ArrayList<Node> reqNodes = nodeMapper.findNode(nodeName);
            System.out.println(reqNodes.size());
            if (reqNodes.size() == 0 || old_nodeName.equals(nodeName)){
                Node node = new Node(nodeName, ipAddress, port, CSV_path, logo, true);
                if (! nodeMapper.update(node, old_nodeName)){
                    throw new Exception("抛出异常");
                }
                message.set(true, "节点修改成功");
            }else{
                message.set(false, "节点名称已存在");
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

    // 删除节点
    @PostMapping("/nodeDelete")
    @ResponseBody
    public HashMap<String, Object> NodeDelete(
            @RequestParam("nodeName") String nodeName,

            @RequestParam("operator") String operator
    ) {
        System.out.println("----- NodeDelete");
        // 填充结果
        HashMap<String, Object> output = TypeFactory.GenerateHMSO();
        Message message = new Message();

        try {
            if (! nodeMapper.delete(nodeName)){
                throw new Exception("抛出异常");
            }
            message.set(true, "节点删除成功");
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