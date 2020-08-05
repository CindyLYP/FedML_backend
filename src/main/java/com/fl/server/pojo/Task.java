package com.fl.server.pojo;

import java.util.ArrayList;
import java.util.HashMap;

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
    private boolean taskStatus;
    private ArrayList<HashMap<String,Double>> metricList;


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

    public boolean getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(boolean taskStatus) {
        this.taskStatus = taskStatus;
    }

    public void StringToMetric(){
        this.metricList = new ArrayList<>();
        for(String items:this.metrics.split("#")){
            String[] item = items.split("\\|");
            HashMap<String,Double> node = new HashMap<>();
            node.put("name",Double.parseDouble(item[0]));
            node.put("value",Double.parseDouble(item[1]));
            this.metricList.add(node);
        }
    }

    public void MetricToString(){
        this.metrics="";
        for (HashMap<String,Double> node:this.metricList){
            this.metrics += String.valueOf(node.get("name"))+"|"+String.valueOf(node.get("value"))+"#";
        }
        this.metrics = this.metrics.substring(0,this.metrics.length()-1);
    }

}
