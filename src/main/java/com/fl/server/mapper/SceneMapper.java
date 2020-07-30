package com.fl.server.mapper;

import com.fl.server.pojo.Scene;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface SceneMapper {
    ArrayList<Scene> getAllScene();
    ArrayList<Scene> selectByInstitution(@Param("institution") String institution);
    ArrayList<Scene> selectBySceneName(@Param("sceneName") String sceneName);
    boolean insert(@Param("scene") Scene scene);
    boolean update(@Param("scene") Scene scene,  String old_sceneName);
    boolean delete(@Param("sceneName") String sceneName);
}
