package com.fl.server.pojo;

import java.security.PublicKey;
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
    private ArrayList<HashMap<String,Object>> trainInfoList;

    public Task(int userId, int sceneId, int datasetId, String taskName, String modelName, String parameters, boolean taskStatus) {
        this.userId = userId;
        this.sceneId = sceneId;
        this.datasetId = datasetId;
        this.taskName = taskName;
        this.modelName = modelName;
        this.parameters = parameters;
        this.taskStatus = taskStatus;
    }
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

    public boolean isTaskStatus() {
        return taskStatus;
    }

    public ArrayList<HashMap<String, Double>> getMetricList() {
        return metricList;
    }

    public void setMetricList(ArrayList<HashMap<String, Double>> metricList) {
        this.metricList = metricList;
    }

    public ArrayList<HashMap<String, Object>> getTrainInfoList() {
        return trainInfoList;
    }

    public void setTrainInfoList(ArrayList<HashMap<String, Object>> trainInfoList) {
        this.trainInfoList = trainInfoList;
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

    public void TrainInfoToDict(){
        this.trainInfoList = new ArrayList<>();
        for(String items:this.trainInfo.split("#")){
            String[] item = items.split("\\|");
            HashMap<String,Object> node = new HashMap<>();
            node.put("time",item[0]);
            node.put("epoch",Integer.valueOf(item[1]));
            node.put("loss",Double.valueOf(item[2]));
            node.put("auc",Double.valueOf(item[3]));
            node.put("ks",Double.valueOf(item[4]));
            this.trainInfoList.add(node);
        }
    }

    public void DictToTrainInfo(){
        this.trainInfo = "";
        for(HashMap<String,Object> node:this.trainInfoList){
            this.trainInfo += String.valueOf(node.get("time"))+"|"+String.valueOf(node.get("epoch"))+"|"+
                    String.valueOf(node.get("loss"))+"|"+String.valueOf(node.get("auc"))+"|"+
                    String.valueOf(node.get("ks"))+"#";
        }
        this.trainInfo = this.trainInfo.substring(0,this.trainInfo.length()-1);
    }

}
