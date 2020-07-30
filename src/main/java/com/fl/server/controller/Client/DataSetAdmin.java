package com.fl.server.controller.Client;

import com.fl.server.mapper.DataDictMapper;
import com.fl.server.mapper.DatasetMapper;
import com.fl.server.object.tools.Message;
import com.fl.server.object.tools.TypeFactory;
import com.fl.server.pojo.Dataset;
import com.fl.server.pojo.Node;
import com.fl.server.pojo.Scene;
import com.fl.server.pojo.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;


// 普通用户-数据管理页
@RestController
@CrossOrigin(origins="*",maxAge = 3600)
public class DataSetAdmin {
    @Autowired
    private DatasetMapper datasetMapper;

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
            ArrayList<Dataset> reqDatasets = datasetMapper.getAllDataset();

            int datasetsNum = reqDatasets.size();
            output.put("datasetsNum", datasetsNum);

            ArrayList<Object> datasets = TypeFactory.GenerateALO();
            for (Dataset reqDataset: reqDatasets){
                HashMap<String, Object> dataset = TypeFactory.GenerateHMSO();
                dataset.put("datasetName", reqDataset);
                dataset.put("alignedNum", reqDataset);
                reqDataset.StringToDict();
                dataset.put("providers", reqDataset.getDict());
                dataset.put("timestamp", reqDataset.getTimestamp());

                datasets.add(dataset);
            }
            output.put("datasets", datasets);
            message.set(true, "场景查询成功");

        }catch (Exception e){
            System.out.println(e.toString());

            message.setState(false);
            message.setMessage("服务器运行异常");
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
            @RequestParam("dict") String dictStr,

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
                System.out.println("gogogo----" + dictStr);
                ArrayList<HashMap<String, Object>> dict = new ArrayList<HashMap<String, Object>>();
                JSONArray jsonArray = new JSONArray(dictStr);
                for (int i = 0; i < jsonArray.length(); i++)    {

                }
//
//                if (! datasetMapper.insert(scene)){
//                    throw new Exception("抛出异常");
//                }
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