package com.fl.server.mapper;

import com.fl.server.pojo.Logger;
import com.fl.server.pojo.Model;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public interface UtilsMapper {
    int DatasetNameToId(@Param("name")String name);
    String IdToDatasetName(@Param("id") int id);

    int TaskNameToId(@Param("name")String name);
    String IdToTaskName(@Param("id") int id);

    int SceneNameToId(@Param("name")String name);
    String IdToSceneName(@Param("id") int id);

    int NodeNameToId(@Param("name")String name);
    String IdToNodeName(@Param("id") int id);

    int UserAccountToId(@Param("name")String name);
    String IdToUserAccount(@Param("id") int id);

    ArrayList<Model> getAllModel();
    Model getModelByName(@Param("name")String name);
    ArrayList<Logger> getLogByUserId(@Param("userId") int userId);
    boolean addLog(@Param("log") Logger log);

    String selectServerMap(@Param("name") String name);
    boolean insertServerMap(@Param("name") String name,@Param("info") String info);




}
