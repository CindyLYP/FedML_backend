package com.fl.server.mapper;

import com.fl.server.pojo.Dataset;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface DatasetMapper {
    ArrayList<Dataset> getAllDataset();
    Dataset selectByDatasetName(@Param("name") String name);
    boolean insert(@Param("dsb") Dataset dsb);
    boolean delete(@Param("name") String name);
    boolean update(@Param("dsb") Dataset dsb);

}
