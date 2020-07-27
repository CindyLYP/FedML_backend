package com.fl.server.object.old;

public class Dataset {
    String taskId;
    String datasetId;
    String filteringRules;
    Double frac;

    public Double getFrac() {
        return frac;
    }

    public void setFrac(Double frac) {
        this.frac = frac;
    }

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
