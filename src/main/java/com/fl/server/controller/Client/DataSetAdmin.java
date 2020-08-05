package com.fl.server.controller.Client;

import com.fl.server.communication.MainServer;
import com.fl.server.mapper.DataDictMapper;
import com.fl.server.mapper.DatasetMapper;
import com.fl.server.mapper.UtilsMapper;
import com.fl.server.object.tools.Message;
import com.fl.server.object.tools.TypeFactory;
import com.fl.server.pojo.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


// 普通用户-数据管理页
@RestController
@CrossOrigin(origins="*",maxAge = 3600)
public class DataSetAdmin {
    @Autowired
    private DatasetMapper datasetMapper;
    @Autowired
    private UtilsMapper utilsMapper;

    MainServer mainServer = new MainServer();

    @PostMapping("/datasetConfigReq")
    @ResponseBody
    public HashMap<String, Object> DatasetConfigReq(
            @RequestParam("scene") String scene,
            @RequestParam("datasetName") String datasetName,
            @RequestParam("operator") String operator
    ) {
        System.out.println("----- DatasetConfigReq");

        // 填充结果
        HashMap<String, Object> output = TypeFactory.GenerateHMSO();
        Message message = new Message();

        try {
            // 处理数据库逻辑
            Dataset reqDataset = datasetMapper.selectByDatasetName(datasetName).get(0);
            output.put("config", reqDataset.getConfig());
            message.set(true, "查询成功");

        }catch (Exception e){
            System.out.println(e.toString());
            message.set(false, "服务器运行异常");
        }finally {
            output.put("message", message);
        }
        return output;
    }


    // 查询数据集
    @PostMapping("/datasetReq")
    @ResponseBody
    public HashMap<String, Object> DatasetReq(
            @RequestParam("scene") String scene,
            @RequestParam("operator") String operator
    ) {
        System.out.println("----- DatasetReq");

        // 填充结果
        HashMap<String, Object> output = TypeFactory.GenerateHMSO();
        Message message = new Message();

        try {
            // 处理数据库逻辑
            ArrayList<Dataset> reqDatasets = datasetMapper.selectByUserIdAndSceneId(utilsMapper.UserAccountToId(operator),
                    utilsMapper.SceneNameToId(scene));

            int datasetsNum = reqDatasets.size();
            output.put("datasetsNum", datasetsNum);

            ArrayList<Object> datasets = TypeFactory.GenerateALO();
            for (Dataset reqDataset: reqDatasets){
                HashMap<String, Object> dataset = TypeFactory.GenerateHMSO();
                dataset.put("datasetName", reqDataset.getDatasetName());
                dataset.put("alignedNum", reqDataset.getAlignedNum());

                reqDataset.StringToDict();
                dataset.put("providers", reqDataset.getDict());
                dataset.put("timestamp", reqDataset.getTimestamp());

                datasets.add(dataset);
            }
            output.put("datasets", datasets);
            message.set(true, "场景查询成功");

        }catch (Exception e){
            System.out.println(e.toString());
            message.set(false, "服务器运行异常");
        }finally {
            output.put("message", message);
        }
        return output;
    }

    // 删除数据集
    @PostMapping("/datasetDelete")
    @ResponseBody
    public HashMap<String, Object> DatasetDelete(
            @RequestParam("scene") String scene,
            @RequestParam("datasetName") String datasetName,

            @RequestParam("operator") String operator
    ) {
        System.out.println("----- DatasetDelete");

        // 填充结果
        HashMap<String, Object> output = TypeFactory.GenerateHMSO();
        Message message = new Message();
        try {
            if (! datasetMapper.delete(datasetName)){
                throw new Exception("抛出异常");
            }
            message.set(true, "数据集删除成功");
        }catch (Exception e){
            System.out.println(e.toString());
            message.set(false, "服务器运行异常");
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
            @RequestParam("dict") String dsStr,
            @RequestParam("config") String config,

            @RequestParam("operator") String operator
    ) {
        System.out.println("----- DatasetGen");
        // 填充结果
        HashMap<String, Object> output = TypeFactory.GenerateHMSO();
        Message message = new Message();
        try {
            // 查询是否名字已经存在
            ArrayList<Dataset> reqDatasets = datasetMapper.selectByDatasetName(datasetName);

            if (reqDatasets.size() == 0){
                ArrayList<HashMap<String, Object>> dict = new ArrayList<HashMap<String, Object>>();
                JSONArray jsonArray = new JSONArray(dsStr);
                for (int i = 0; i < jsonArray.length(); i++)    {
                    HashMap<String, Object> pair = new HashMap<String, Object>();
                    JSONObject json = jsonArray.getJSONObject(i);

                    pair.put("provider", json.getString("provider"));

                    JSONArray arr = json.getJSONArray("attributes");
                    ArrayList<String> attributes = new ArrayList<String>();
                    for(int j = 0; j < arr.length(); j++){
                        attributes.add(arr.getString(j));
                    }
                    pair.put("attributes", attributes);
                    dict.add(pair);
                }
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Dataset dataset = new Dataset(utilsMapper.UserAccountToId(operator), utilsMapper.SceneNameToId(scene),
                        datasetName, -1, format.format((new Date()).getTime()), dict, config);

                HashMap<String, Object> params = TypeFactory.GenerateHMSO();
                params.put("task_name", dataset.getDatasetName());

                dataset.dictToString();
                if (! datasetMapper.insert(dataset)){
                    throw new Exception("抛出异常");
                }
//
                // commit the align mission
                JSONObject json = mainServer.alignDataset(dataset);
                if(! "ok".equals(json.getString("status"))){
                    System.out.println(json.getString("msg"));
                    throw new Exception("engine problem");
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
}