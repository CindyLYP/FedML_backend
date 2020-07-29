package com.fl.server.pojo;

import java.util.ArrayList;

public class Task {
    private int id;
    private int userId;
    private int sceneId;
    private int datasetId;
    private String taskName;
    private String modelName;
    private String parameters;
    private String metrics;
    private String trainInfo;
    private String taskStatus;
    private ArrayList<String> metricNameList;
    private ArrayList<Double> metricValueList;

    public Task(){}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSceneId() {
        return sceneId;
    }

    public void setSceneId(int sceneId) {
        this.sceneId = sceneId;
    }

    public int getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(int datasetId) {
        this.datasetId = datasetId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getMetrics() {
        return metrics;
    }

    public void setMetrics(String metrics) {
        this.metrics = metrics;
    }

    public String getTrainInfo() {
        return trainInfo;
    }

    public void setTrainInfo(String trainInfo) {
        this.trainInfo = trainInfo;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public ArrayList<String> getMetricNameList() {
        return metricNameList;
    }

    public void setMetricNameList(ArrayList<String> metricNameList) {
        this.metricNameList = metricNameList;
    }

    public ArrayList<Double> getMetricValueList() {
        return metricValueList;
    }

    public void setMetricValueList(ArrayList<Double> metricValueList) {
        this.metricValueList = metricValueList;
    }
    public void StringToMetric(){
        this.metricNameList = new ArrayList<>();
        this.metricValueList = new ArrayList<>();
        for(String metric:this.metrics.split("#")){
            String[] str = metric.split("|");
            this.metricNameList.add(str[0]);
            this.metricValueList.add(Double.parseDouble(str[1]));
        }
    }

    public void MetricToString(){
        int i=0;
        this.metrics="";
        for(;i<this.metricValueList.size()-1;i++){
            this.metrics += this.metricNameList.get(i)+"|"+String.valueOf(this.metricValueList.get(i))+"#";
        }
        i = this.metricValueList.size()-1;
        this.metrics += this.metricNameList.get(i)+"|"+String.valueOf(this.metricValueList.get(i));
    }

}
