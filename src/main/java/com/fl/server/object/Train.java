package com.fl.server.object;

public class Train {
    String taskId;
    String trainId;
    String modelType;
    int paramDim;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    public int getParamDim() {
        return paramDim;
    }

    public void setParamDim(int paramDim) {
        this.paramDim = paramDim;
    }
}
