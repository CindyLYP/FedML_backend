package com.fl.server.communication;

import com.fl.server.mapper.DatasetMapper;
import com.fl.server.pojo.Dataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin(origins="*",maxAge = 3600)
public class UpdateDatabase {
    @Autowired
    private DatasetMapper datasetMapper;
    @CrossOrigin
    @PostMapping(value = "/updateDataset")
    @ResponseBody
    public String updateDataset(@RequestBody Dataset dataset){
        System.out.println("rec dataset from server backend");
        System.out.println(dataset.getDatasetName()+String.valueOf(dataset.getAlignedNum()));
        dataset.setId(datasetMapper.selectByDatasetName(dataset.getDatasetName()).get(0).getId());
        datasetMapper.update(dataset);
        return "update dataset table in database success";
    }

}
