package com.fl.server.controller.ServerAdmin;

import com.fl.server.mapper.NodeMapper;
import com.fl.server.object.NodeRef.NodeInfo;
import com.fl.server.object.tools.Message;
import com.fl.server.object.tools.TypeFactor;
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
        // 填充结果
        HashMap<String, Object> output = TypeFactor.GenerateHMSO();
        Message message = new Message();

        try {
            // 处理数据库逻辑
            ArrayList<Node> reqNodes = nodeMapper.getAllNode();
            int nodesNum = reqNodes.size();
            output.put("nodesNum", nodesNum);

            ArrayList<Object> nodes = TypeFactor.GenerateALO();
            for(Node reqNode: reqNodes){
                HashMap<String, Object> node = TypeFactor.GenerateHMSO();
                node.put("nodeName", reqNode.getNodeName());
                node.put("ipAddress", reqNode.getIpAddress());
                node.put("port", reqNode.getPort());
                // node.put("status", reqNode.get);         // 缺少获取节点状态
                node.put("logo", reqNode.getLogo());

                nodes.add(node);
            }
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
        // 填充结果
        HashMap<String, Object> output = TypeFactor.GenerateHMSO();
        Message message = new Message();

        try {
            //

            Node node = new Node();
            node.setNodeName(nodeName);
            node.setIpAddress(ipAddress);
            node.setPort(port);
            node.setIpAddress(ipAddress);
            node.setCsvPath(CSV_path);
            // node.setLogo(logo);

            nodeMapper.insert(node);
            message.set(true, "节点创建成功");
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
            Node node = new Node();
            node.setNodeName(nodeName);
            node.setIpAddress(ipAddress);
            node.setPort(port);
            node.setIpAddress(ipAddress);
            node.setCsvPath(CSV_path);
            // node.setLogo(logo);

            nodeMapper.update(node);

            message.setState(true);
            message.setMessage("节点修改成功");
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
        // 填充结果
        HashMap<String, Object> output = TypeFactor.GenerateHMSO();
        Message message = new Message();

        try {
            boolean status = nodeMapper.delete(nodeName);
            message.setState(status);
            message.setMessage("节点删除成功");
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