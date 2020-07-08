package com.fl.server.mapper;


import com.fl.server.object.Dataset;
import com.fl.server.object.Task;
import com.fl.server.object.Train;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface TaskMapper {
    Boolean createTaskByEmail(@Param("task") Task task);
    Boolean createTrainByTask(@Param("train") Train train);
    Boolean createDatasetByTask(@Param("dataset") Dataset dataset);

    Task selectTaskByEmail(@Param("email") String email);
    ArrayList<Train> selectTrainByTask(@Param("taskId") String taskId);
    ArrayList<Dataset> selectDatasetByTask(@Param("taskId") String taskId);

    Boolean deleteTask(@Param("taskId") String taskId);
    Boolean deleteTrain(@Param("trainId") String trainId);
    Boolean deleteDataset(@Param("datasetId") String datasetId);


}
