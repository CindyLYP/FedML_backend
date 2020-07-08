package com.fl.server.mapper;


import com.fl.server.object.Dataset;
import com.fl.server.object.Task;
import com.fl.server.object.Train;
import org.apache.ibatis.annotations.Param;

public interface TaskMapper {
    Boolean createTaskByEmail(@Param("email") String email, @Param("taskId") String taskId, @Param("desc") String desc);
    Boolean createTrainByTask(@Param("taskId") String taskId,@Param("trainId") String trainId,
                              @Param("modelType") String modelType,@Param("paramDim") int paramDim);
    Boolean createDatasetByTask(@Param("taskId") String taskId,@Param("datasetId") String datasetId,
                                @Param("features") String features);

    Task selectTaskByEmail(@Param("email") String email);
    Train selectTrainByTask(@Param("taskId") String taskId);
    Dataset selectDatasetByTask(@Param("taskId") String taskId);

    Boolean deleteTask(@Param("taskId") String taskId);
    Boolean deleteTrain(@Param("trainId") String trainId);
    Boolean deleteDataset(@Param("datasetId") String datasetId);


}
