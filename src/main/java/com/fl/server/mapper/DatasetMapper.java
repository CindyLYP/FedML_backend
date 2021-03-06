package com.fl.server.mapper;

import com.fl.server.pojo.Dataset;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface DatasetMapper {
    ArrayList<Dataset> getAllDataset();
    ArrayList<Dataset> selectByDatasetName(@Param("name") String name);
    ArrayList<Dataset> selectByUserId(@Param("id") int id);
    ArrayList<Dataset> selectByUserIdAndSceneId(@Param("userId") int userId,@Param("sceneId") int sceneId);
    boolean insert(@Param("dsb") Dataset dsb);
    boolean delete(@Param("name") String name);
    boolean update(@Param("dsb") Dataset dsb);


}
