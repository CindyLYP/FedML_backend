package com.fl.server.controller.Client;

import com.fl.server.communication.MainServer;
import com.fl.server.communication.QueryServer;
import com.fl.server.mapper.DatasetMapper;
import com.fl.server.mapper.TaskMapper;
import com.fl.server.mapper.UtilsMapper;
import com.fl.server.object.tools.Message;
import com.fl.server.object.tools.TypeFactory;
import com.fl.server.pojo.Dataset;
import com.fl.server.pojo.Model;
import com.fl.server.pojo.Task;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


// 普通用户-模型训练页
@RestController
public class ModelTrain {
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private UtilsMapper utilsMapper;
    @Autowired
    private MainServer mainServer;
    @Autowired
    private QueryServer queryServer;



    // 任务查询
    @PostMapping("/taskReq")
    @ResponseBody
    public HashMap<String, Object> TaskReq(
            @RequestParam("scene") String scene,

            @RequestParam("operator") String operator
    ) {
        System.out.println("----- TaskReq");
        // 填充结果
        HashMap<String, Object> output = TypeFactory.GenerateHMSO();
        Message message = new Message();

        try {
            // 处理数据库逻辑
            ArrayList<Task> reqTasks = taskMapper.selectByUserAndScene(utilsMapper.UserAccountToId(operator),
                    utilsMapper.SceneNameToId(scene));

            int tasksNum = reqTasks.size();
            output.put("tasksNum", tasksNum);

            ArrayList<Object> tasks = TypeFactory.GenerateALO();
            for (Task reqTask: reqTasks){
                HashMap<String, Object> task = TypeFactory.GenerateHMSO();
                task.put("taskName", reqTask.getTaskName());
                task.put("datasetName", utilsMapper.IdToDatasetName(reqTask.getDatasetId()));
                task.put("modelName", reqTask.getModelName());
                task.put("parameters", reqTask.getParameters());
                task.put("status", reqTask.getTaskStatus());

                System.out.println("test1");
                if ("训练完成".equals(reqTask.getTaskStatus())){
                    reqTask.TrainInfoToDict();
                    task.put("metrics", reqTask.getTrainInfoList().get(reqTask.getTrainInfoList().size()-1));
                }else{
                    task.put("ks",-1);
                    task.put("auc",-1);
                }
                System.out.println("test2");

                tasks.add(task);
            }
            output.put("tasks", tasks);
            message.set(true, "任务查询成功");

        }catch (Exception e){
            System.out.println(e.toString());
            message.set(false, "服务器运行异常");
        }finally {
            output.put("message", message);
        }
        return output;
    }

    // 训练效果
    @PostMapping("/modelReq")
    @ResponseBody
    public HashMap<String, Object> ModelReq(
            @RequestParam("operator") String operator
    ) {
        System.out.println("----- modelReq");
        // 填充结果
        HashMap<String, Object> output = TypeFactory.GenerateHMSO();
        Message message = new Message();

        try {
            ArrayList<Model> reqModels = utilsMapper.getAllModel();
            ArrayList<Object> models = TypeFactory.GenerateALO();
            output.put("modelNum", reqModels.size());
            for(Model reqModel:reqModels){
                // reqModel.stringToList();
                // HashMap<String, Object> model = TypeFactory.GenerateHMSO();
                // model.put("modelName", reqModel.getName());
                // model.put("defaultParams", reqModel.getParams_list());
                // models.add(model);
                models.add(reqModel.getName());
            }
            output.put("models", models);
            message.set(true, "查询成功");
        }catch (Exception e){
            System.out.println(e.toString());
            message.set(false, "服务器运行异常");
        }finally {
            output.put("message", message);
        }
        return output;
    }

    @PostMapping("/modelInfoReq")
    @ResponseBody
    public HashMap<String, Object> ModelInfoReq(
            @RequestParam("modelName") String modelName,
            @RequestParam("operator") String operator
    ) {
        // 填充结果
        HashMap<String, Object> output = TypeFactory.GenerateHMSO();
        Message message = new Message();

        try {
            Model reqModel = utilsMapper.getModelByName(modelName);
            reqModel.stringToList();

            output.put("defaultParams", reqModel.getParams_list());
            message.set(true, "查询成功");
        }catch (Exception e){
            System.out.println(e.toString());
            message.set(false, "服务器运行异常");
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
            @RequestParam("parameters") String parameters,

            @RequestParam("operator") String operator
    ) {
        // 填充结果
        HashMap<String, Object> output = TypeFactory.GenerateHMSO();
        Message message = new Message();


        try {
            // 查询是否名字已经存在
            ArrayList<Task> reqTasks = taskMapper
                    .selectByTaskName(taskName);

            if (reqTasks.size() == 0){
                Task task = new Task(utilsMapper.UserAccountToId(operator), utilsMapper.SceneNameToId(scene),
                        utilsMapper.DatasetNameToId(datasetName), taskName, modelName, parameters, "等待运行") ;

                if (! taskMapper.insert(task)){
                    throw new Exception("task 抛出异常");
                }

                JSONObject json = mainServer.trainTask(task);
                if(! "ok".equals(json.getString("status"))){
                    System.out.println(json.getString("msg"));
                    throw new Exception("engine problem");
                }

// commit the align mission
//                JSONObject json = mainServer.alignDataset(dataset);
//                if(! "ok".equals(json.getString("status"))){
//                    System.out.println(json.getString("msg"));
//                    throw new Exception("engine problem");
//                }
                message.set(true, "任务创建成功");
            }else{
                message.set(false, "任务名称已存在");
            }
        }catch (Exception e){
            System.out.println(e.toString());
            message.set(false, "服务器运行异常");
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
        HashMap<String, Object> output = TypeFactory.GenerateHMSO();
        Message message = new Message();

        try {
            if (! taskMapper.delete(taskName)){
                throw new Exception("抛出异常");
            }
            message.set(true, "任务删除成功");
        }catch (Exception e){
            System.out.println(e.toString());
            message.set(false, "服务器运行异常");
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
        HashMap<String, Object> output = TypeFactory.GenerateHMSO();
        Message message = new Message();

        try {

            message.set(true, "训练重启成功");
        }catch (Exception e){
            System.out.println(e.toString());
            message.set(false, "服务器运行异常");
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
        HashMap<String, Object> output = TypeFactory.GenerateHMSO();
        Message message = new Message();
        try {
            String status = taskMapper.selectByTaskName(taskName).get(0).getTaskStatus();
            if("训练完成".equals(status)){
                output.put("status", "训练完成");
            }else{
                output.put("status", "正在训练");
            }
            // output.put("status", "等待训练");
            message.set(true, "查询成功");
        }catch (Exception e){
            System.out.println(e.toString());
            message.set(false, "服务器运行异常");
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
        HashMap<String, Object> output = TypeFactory.GenerateHMSO();
        Message message = new Message();

        try {
            Task task = taskMapper.selectByTaskName(taskName).get(0);

            String status = task.getTaskStatus();
            if("训练完成".equals(status)){
                output.put("status", "训练完成");
            }else if("训练失败".equals(status)){
                output.put("status", "训练失败");
            }
            else {
                output.put("status", "训练中");
            }
            task.TrainInfoToDict();
            ArrayList<HashMap<String, Object>> infoList = task.getTrainInfoList();
            int roundNum = infoList.size();
            output.put("roundNum", roundNum);
            ArrayList<Object> roundInfo = TypeFactory.GenerateALO();
            for (int i = 0; i < roundNum; ++i) {
                HashMap<String, Object> round = TypeFactory.GenerateHMSO();
                round.put("round", i + 1);
                round.put("auc", infoList.get(i).get("auc"));
                round.put("ks", infoList.get(i).get("ks"));

                roundInfo.add(round);
            }
            output.put("roundInfo", roundInfo);
            message.set(true, "训练信息查询成功");
        } catch (Exception e) {
            System.out.println(e.toString());
            message.set(false, "服务器运行异常");
        } finally {
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
        HashMap<String, Object> output = TypeFactory.GenerateHMSO();
        Message message = new Message();


        try {
            output.put("status", "仅用于测试: 训练成功");
            HashMap<String, Object> result = TypeFactory.GenerateHMSO();
            result.put("accuracy", 0.61);
            result.put("F1", 2*0.7*0.5/(0.7+0.5));
            result.put("precision", 0.7);
            result.put("recall", 0.5);
            result.put("FBeta", 0.53);
            result.put("logLoss", 10.2333);
            result.put("Roc", 0.65);

            output.put("result", result);
            message.setState(true);
            message.setMessage("训练效果查询成功");
        }catch (Exception e){
            System.out.println(e.toString());
            message.set(false, "服务器运行异常");
        }finally {
            output.put("message", message);
        }
        return output;
    }


}
