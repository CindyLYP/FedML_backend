package com.fl.server.mapper;

import org.apache.ibatis.annotations.Param;

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
}
