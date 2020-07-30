package com.fl.server.controller.NodeAdmin;

import com.fl.server.mapper.DataDictMapper;
import com.fl.server.mapper.UserMapper;
import com.fl.server.mapper.UtilsMapper;
import com.fl.server.object.tools.Message;
import com.fl.server.object.tools.TypeFactory;
import com.fl.server.pojo.DataDict;
import com.fl.server.pojo.Scene;
import com.fl.server.pojo.User;
import lombok.experimental.UtilityClass;
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
    @Autowired
    private UtilsMapper utilsMapper;
    // 查询数据字典
    @PostMapping("/attributeReq")
    @ResponseBody
    public HashMap<String, Object> AttributeReq(
            @RequestParam("specific") boolean specific,

            @RequestParam("operator") String operator
    ) {
        System.out.println("----- AttributeReq");
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

            ArrayList<Object> attributes = TypeFactory.GenerateALO();
            for (DataDict reqDataDict: reqDataDicts){
                HashMap<String, Object> attribute = TypeFactory.GenerateHMSO();
                attribute.put("attrName", reqDataDict.getAttrName());
                attribute.put("cnName", reqDataDict.getCnName());
                attribute.put("describe", reqDataDict.getDescription());
                attribute.put("dataType", reqDataDict.getDataType());
                attribute.put("class", reqDataDict.getAttrClass());
                attribute.put("provider", reqDataDict.getProvider());
                attribute.put("area", reqDataDict.getArea());

                attributes.add(attribute);
            }
            output.put("attributes", attributes);
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
        System.out.println("----- AttributeUpdate");
        // 填充结果
        HashMap<String, Object> output = TypeFactory.GenerateHMSO();
        Message message = new Message();

        try {
            if (csv.isEmpty()) {
                message.set(false, "文件上传失败");
            }
            else {
                // String fileName = csv.getOriginalFilename();
                // File dest = new File("./tmp/" + fileName);
                // csv.transferTo(dest);
                // message.set(true, "文件上传成功");

                Reader reader = null;
                reader = new InputStreamReader(csv.getInputStream(), "utf-8");
                BufferedReader br = new BufferedReader(reader);
                String line;
                while ((line = br.readLine()) != null) {
                    if(!"".equals(line)){
                        System.out.println(line);
                        String[] values = line.split(",");
                        System.out.println(values[5]);
                        System.out.println(utilsMapper.NodeNameToId(values[5]));

                        DataDict dataDict = new DataDict(utilsMapper.NodeNameToId(values[5]), values[0], values[1], values[2],
                                values[3], values[4], values[5], "待查");
                        if(! dataDictMapper.insert(dataDict)){
                            throw new Exception("抛出异常");
                        }
                    }
                }
                reader.close();
            }

            message.set(true, "数据字典上传成功");

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