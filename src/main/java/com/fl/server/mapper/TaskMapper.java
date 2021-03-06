package com.fl.server.mapper;

import com.fl.server.pojo.Task;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface TaskMapper {
    ArrayList<Task> selectByUserAndScene(@Param("userId") int userId,@Param("sceneId") int sceneId);
    ArrayList<Task> selectByTaskName(@Param("taskName") String taskName);
    boolean insert(@Param("task") Task task);
    boolean update(@Param("task") Task task);
    boolean updateTaskStatus(@Param("id")int id,@Param("status")String status);
    boolean delete(@Param("taskName") String taskName);

}
