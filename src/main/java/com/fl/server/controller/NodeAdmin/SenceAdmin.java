package com.fl.server.controller.NodeAdmin;

import com.fl.server.mapper.DataDictMapper;
import com.fl.server.mapper.NodeMapper;
import com.fl.server.mapper.SceneMapper;
import com.fl.server.mapper.UserMapper;
import com.fl.server.object.tools.Message;
import com.fl.server.object.tools.TypeFactory;
import com.fl.server.pojo.DataDict;
import com.fl.server.pojo.Node;
import com.fl.server.pojo.Scene;
import com.fl.server.pojo.User;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// 节点管理-场景设置页
@RestController
@CrossOrigin(origins="*",maxAge = 3600)
public class SenceAdmin {
    @Autowired
    private SceneMapper sceneMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private NodeMapper nodeMapper;
    @Autowired
    private DataDictMapper dataDictMapper;
    // 查询场景
    @PostMapping("/sceneReq")
    @ResponseBody
    public HashMap<String, Object> SceneReq(
            @RequestParam("specific") boolean specific,

            @RequestParam("operator") String operator
    ) {
        System.out.println("----- SceneReq");
        // 填充结果
        HashMap<String, Object> output = TypeFactory.GenerateHMSO();
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
            ArrayList<Object> scenes = TypeFactory.GenerateALO();

            for (int i = reqScenes.size()-1; i >=0; i--){
                Scene reqScene = reqScenes.get(i);
                HashMap<String, Object> scene = TypeFactory.GenerateHMSO();
                scene.put("sceneName", reqScene.getSceneName());
                scene.put("institution", reqScene.getInstitution());
                scene.put("target", reqScene.getTarget());
                reqScene.StringToDict();
                scene.put("describe", reqScene.getDescriptionList());

                Node node = nodeMapper.findNode(reqScene.getInstitution()).get(0);
                scene.put("logo", node.getLogo());

                scenes.add(scene);
            }


            output.put("scenes", scenes);
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
            @RequestParam("describe") String describeStr,
            @RequestParam("operator") String operator
    ) {
        System.out.println("----- SceneCreate");
        // 填充结果
        HashMap<String, Object> output = TypeFactory.GenerateHMSO();
        Message message = new Message();

        try {
            // 获取用户
            User user = userMapper.selectByAccount(operator).get(0);
            // 查询是否名字已经存在
            ArrayList<Scene> reqScenes = sceneMapper.selectBySceneName(sceneName);

            if (reqScenes.size() == 0){
                boolean flag = false;
                ArrayList<DataDict> reqDataDicts = dataDictMapper.selectByProvider(user.getInstitution());
                for(DataDict dataDict:reqDataDicts){
                    if(dataDict.getAttrName().equals(target)){
                        flag = true;
                        break;
                    }
                }
                if(!flag){
                    message.set(false, "目标字段不存在数据字典里");
                }else{
                    System.out.println("gogogo----" + describeStr);
                    // describeStr = "[{\"value\":\"1\", \"label\":\"2\"}]";
                    ArrayList<HashMap<String, String>> describe = new ArrayList<HashMap<String, String>>();
                    JSONArray jsonArray = new JSONArray(describeStr);
                    for (int i = 0; i < jsonArray.length(); i++)    {
                        HashMap<String, String> pair = new  HashMap<String, String>();
                        JSONObject json = jsonArray.getJSONObject(i);
                        pair.put("value", json.getString("value"));
                        pair.put("label", json.getString("label"));
                        describe.add(pair);
                    }
                    Scene scene = new Scene(user.getInstitution(), sceneName, target, describe);
                    scene.dictToString();
                    if (! sceneMapper.insert(scene)){
                        throw new Exception("抛出异常");
                    }
                    message.set(true, "场景创建成功");
                }
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
            @RequestParam("old_sceneName") String old_sceneName,
            @RequestParam("target") String target,
            @RequestParam("describe") String describeStr,

            @RequestParam("operator") String operator
    ) {
        System.out.println("----- SceneModify");
        // 填充结果
        HashMap<String, Object> output = TypeFactory.GenerateHMSO();
        Message message = new Message();


        try {
            // 获取用户
            User user = userMapper.selectByAccount(operator).get(0);
            // 查询是否名字已经存在
            ArrayList<Scene> reqScenes = sceneMapper.selectBySceneName(sceneName);

            if (reqScenes.size() == 0 || sceneName.equals(old_sceneName)) {
                ArrayList<HashMap<String, String>> describe = new ArrayList<HashMap<String, String>>();
                JSONArray jsonArray = new JSONArray(describeStr);
                for (int i = 0; i < jsonArray.length(); i++)    {
                    HashMap<String, String> pair = new  HashMap<String, String>();
                    JSONObject json = jsonArray.getJSONObject(i);
                    pair.put("value", json.getString("value"));
                    pair.put("label", json.getString("label"));
                    describe.add(pair);
                }
                Scene scene = new Scene(user.getInstitution(), sceneName, target, describe);
                scene.dictToString();
                if (! sceneMapper.update(scene, old_sceneName)){
                    throw new Exception("抛出异常");
                }
                message.set(true, "场景修改成功");
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

    // 删除场景
    @PostMapping("/sceneDelete")
    @ResponseBody
    public HashMap<String, Object> SceneDelete(
            @RequestParam("sceneName") String sceneName,

            @RequestParam("operator") String operator
    ) {
        System.out.println("----- SceneDelete");
        // 填充结果
        HashMap<String, Object> output = TypeFactory.GenerateHMSO();
        Message message = new Message();
        try {
            if (! sceneMapper.delete(sceneName)){
                throw new Exception("抛出异常");
            }
            message.set(true, "场景删除成功");
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