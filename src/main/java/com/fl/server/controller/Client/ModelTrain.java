package com.fl.server.controller.Client;

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
                if ("已完成".equals(reqTask.getTaskStatus())){
                    reqTask.StringToMetric();
                    task.put("metrics", reqTask.getMetrics());
                }
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
                    .selectByUserAndScene(utilsMapper.UserAccountToId(operator), utilsMapper.SceneNameToId(scene));

            if (reqTasks.size() == 0){
                Task task = new Task(utilsMapper.UserAccountToId(operator), utilsMapper.SceneNameToId(scene),
                        utilsMapper.DatasetNameToId(datasetName), taskName, modelName, parameters, "等待运行") ;

                if (! taskMapper.insert(task)){
                    throw new Exception("task 抛出异常");
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
            // output.put("status", "等待训练");
            output.put("status", "仅用于测试: 训练成功");
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
            output.put("status", "仅用于测试: 训练成功");
            output.put("roundNum", 5);

            ArrayList<Object> roundInfo = TypeFactory.GenerateALO();
            for (int i = 0; i < 5; ++i) {
                HashMap<String, Object> round = TypeFactory.GenerateHMSO();
                round.put("round", i + 1);
                round.put("auc", Math.sqrt(i * 10) / Math.sqrt(5.7 * 10));
                round.put("ks", 1.0 / (1.0 + Math.exp(i - 2)));
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
            HashMap<String, Object> round = TypeFactory.GenerateHMSO();
            round.put("accuracy", 0.61);
            round.put("F1", 2*0.7*0.5/(0.7+0.5));
            round.put("precision", 0.7);
            round.put("recall", 0.5);
            round.put("FBeta", 0.53);
            round.put("logLoss", 10.2333);
            round.put("Roc", 0.65);


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
