package com.fl.server.object;

public class Dataset {
    String taskId;
    String datasetId;
    String filteringRules;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(String datasetId) {
        this.datasetId = datasetId;
    }

    public String getFilteringRules() {
        return filteringRules;
    }

    public void setFilteringRules(String filteringRules) {
        this.filteringRules = filteringRules;
    }
}
