package com.fl.server.controller.NodeAdmin;

import com.fl.server.mapper.SceneMapper;
import com.fl.server.mapper.UserMapper;
import com.fl.server.object.tools.Message;
import com.fl.server.object.tools.TypeFactor;
import com.fl.server.pojo.Node;
import com.fl.server.pojo.Scene;
import com.fl.server.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

// 节点管理-场景设置页
@RestController
@CrossOrigin(origins="*",maxAge = 3600)
public class SenceAdmin {
    @Autowired
    private SceneMapper sceneMapper;
    @Autowired
    private UserMapper userMapper;

    // 查询场景
    @PostMapping("/sceneReq")
    @ResponseBody
    public HashMap<String, Object> SceneReq(
            @RequestParam("specific") boolean specific,

            @RequestParam("operator") String operator
    ) {
        System.out.println("----- SceneReq");
        // 填充结果
        HashMap<String, Object> output = TypeFactor.GenerateHMSO();
        Message message = new Message();

        try {
            // 获取用户
            User user = userMapper.selectByAccount(operator).get(0);

            // 处理数据库逻辑
            ArrayList<Scene> reqScenes;
            if (specific){
                reqScenes = sceneMapper.selectByInstitution(user.getInstitution());
            }else {
                reqScenes = sceneMapper.getAllScene();
            }
            int scenesNum = reqScenes.size();
            output.put("scenesNum", scenesNum);
            ArrayList<Object> scenes = TypeFactor.GenerateALO();
            for (Scene reqScene: reqScenes){
                HashMap<String, Object> node = TypeFactor.GenerateHMSO();
                node.put("sceneName", reqScene.getSceneName());
                node.put("institution", reqScene.getInstitution());
                node.put("target", reqScene.getTarget());
                node.put("describe", reqScene.getDescription());

                scenes.add(node);
            }
            message.set(true, "场景查询成功");
        }catch (Exception e){
            System.out.println(e.toString());
            message.set(false, "服务器运行异常");

        }finally {
            output.put("message", message);
        }
        return output;
    }


    // 创建场景
    @PostMapping("/sceneCreate")
    @ResponseBody
    public HashMap<String, Object> SceneCreate(
            @RequestParam("sceneName") String sceneName,
            @RequestParam("target") String target,
            @RequestParam("describe") ArrayList<HashMap<String, String>> describe,

            @RequestParam("operator") String operator
    ) {
        System.out.println("----- SceneCreate");
        // 填充结果
        HashMap<String, Object> output = TypeFactor.GenerateHMSO();
        Message message = new Message();


        try {
            // 获取用户
            User user = userMapper.selectByAccount(operator).get(0);
            // 查询是否名字已经存在
            ArrayList<Scene> reqScenes = sceneMapper.selectBySceneName(sceneName);

            if (reqScenes.size() == 0){
                Scene scene = new Scene(user.getInstitution(), sceneName, target, "NONE");
                if (! sceneMapper.insert(scene)){
                    throw new Exception("抛出异常");
                }
                message.set(true, "场景创建成功");
            }else{
                message.set(false, "场景名称已存在");
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


    // 修改场景
    @PostMapping("/sceneModify")
    @ResponseBody
    public HashMap<String, Object> SceneModify(
            @RequestParam("sceneName") String sceneName,
            @RequestParam("sceneName") String old_sceneName,
            @RequestParam("target") String target,
            @RequestParam("describe") ArrayList<HashMap<String, String>> describe,

            @RequestParam("operator") String operator
    ) {
        System.out.println("----- SceneModify");
        // 填充结果
        HashMap<String, Object> output = TypeFactor.GenerateHMSO();
        Message message = new Message();


        try {
            // 获取用户
            User user = userMapper.selectByAccount(operator).get(0);
            // 查询是否名字已经存在
            ArrayList<Scene> reqScenes = sceneMapper.selectBySceneName(sceneName);
            if (reqScenes.size() == 0 || sceneName.equals(old_sceneName)) {
                Scene scene = new Scene(user.getInstitution(), sceneName, target, "NONE");
                if (! sceneMapper.insert(scene)){
                    throw new Exception("抛出异常");
                }
                message.set(true, "场景修改成功");
            }else{
                message.set(false, "场景名称已存在");
            }
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
        System.out.println("----- SceneDelete");
        // 填充结果
        HashMap<String, Object> output = TypeFactor.GenerateHMSO();
        Message message = new Message();
        try {
            if (! sceneMapper.delete(sceneName)){
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