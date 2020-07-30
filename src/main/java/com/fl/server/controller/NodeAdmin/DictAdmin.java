package com.fl.server.controller.NodeAdmin;

import com.fl.server.mapper.DataDictMapper;
import com.fl.server.mapper.UserMapper;
import com.fl.server.object.tools.Message;
import com.fl.server.object.tools.TypeFactory;
import com.fl.server.pojo.DataDict;
import com.fl.server.pojo.Scene;
import com.fl.server.pojo.User;
import org.hibernate.id.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;

// 节点管理-数据字典页
@RestController
@CrossOrigin(origins="*",maxAge = 3600)
public class DictAdmin {
    @Autowired
    private DataDictMapper dataDictMapper;
    @Autowired
    private UserMapper userMapper;

    // 查询数据字典
    @PostMapping("/attributeReq")
    @ResponseBody
    public HashMap<String, Object> AttributeReq(
            @RequestParam("specific") boolean specific,

            @RequestParam("operator") String operator
    ) {
        // 填充结果
        HashMap<String, Object> output = TypeFactory.GenerateHMSO();
        Message message = new Message();

        try {
            // 获取用户
            User user = userMapper.selectByAccount(operator).get(0);

            // 处理数据库逻辑
            ArrayList<DataDict> reqDataDicts;
            if (specific){
                reqDataDicts = dataDictMapper.selectByProvider(user.getInstitution());
            }else {
                reqDataDicts = dataDictMapper.getAllDataDict();
            }

            int attributesNum = reqDataDicts.size();
            output.put("attributesNum", attributesNum);

            ArrayList<Object> dataDicts = TypeFactory.GenerateALO();
            for (DataDict reqDataDict: reqDataDicts){
                HashMap<String, Object> dataDict = TypeFactory.GenerateHMSO();
                dataDict.put("attrName", reqDataDict.getAttrName());
                dataDict.put("cnName", reqDataDict.getCnName());
                dataDict.put("describe", reqDataDict.getDescription());
                dataDict.put("dataType", reqDataDict.getDataType());
                dataDict.put("class", reqDataDict.getAttrClass());
                dataDict.put("provider", reqDataDict.getProvider());
                dataDict.put("area", reqDataDict.getArea());

                dataDicts.add(dataDict);
            }

            message.set(true, "数据字典查询成功");
        } catch (Exception e) {
            System.out.println(e.toString());
            message.set(false, "服务器运行异常");
        } finally {
            output.put("message", message);
        }
        return output;
    }

    // 更新数据字典
    @PostMapping("/attributeUpdate")
    @ResponseBody
    public HashMap<String, Object> AttributeUpdate(
            @RequestParam("csv") MultipartFile csv,
            @RequestParam("operator") String operator
    ) {
        // 填充结果
        HashMap<String, Object> output = TypeFactory.GenerateHMSO();
        Message message = new Message();

        try {
            if (csv.isEmpty()) {
                message.set(false, "文件上传失败");
            }
            else {
                String fileName = csv.getOriginalFilename();
                File dest = new File("./tmp/" + fileName);
                csv.transferTo(dest);
                message.set(true, "文件上传成功");

                Reader reader = null;
                reader = new InputStreamReader(csv.getInputStream(), "utf-8");
                BufferedReader br = new BufferedReader(reader);
                String line;
                while ((line = br.readLine()) != null) {
                    if(!"".equals(line)){
                        String[] values = line.split(",");
                    }
                }
                reader.close();
            }

            message.setState(true);

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