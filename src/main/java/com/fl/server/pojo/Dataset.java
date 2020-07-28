package com.fl.server.pojo;

import java.util.ArrayList;

public class Dataset {
    private int id;
    private int userId;
    private int sceneId;
    private String attrInfo;
    private String datasetName;
    private int alignedNum;
    private String timestamp;
    private ArrayList<Integer> attrIdList;
    private ArrayList<String> attrNameList;
    private ArrayList<String> providerList;

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

    public String getAttrInfo() {
        return attrInfo;
    }

    public void setAttrInfo(String attrInfo) {
        this.attrInfo = attrInfo;
    }

    public String getDatasetName() {
        return datasetName;
    }

    public void setDatasetName(String datasetName) {
        this.datasetName = datasetName;
    }

    public int getAlignedNum() {
        return alignedNum;
    }

    public void setAlignedNum(int alignedNum) {
        this.alignedNum = alignedNum;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public ArrayList<Integer> getAttrIdList() {
        return attrIdList;
    }

    public void setAttrIdList(ArrayList<Integer> attrIdList) {
        this.attrIdList = attrIdList;
    }

    public ArrayList<String> getAttrNameList() {
        return attrNameList;
    }

    public void setAttrNameList(ArrayList<String> attrNameList) {
        this.attrNameList = attrNameList;
    }

    public ArrayList<String> getProviderList() {
        return providerList;
    }

    public void setProviderList(ArrayList<String> providerList) {
        this.providerList = providerList;
    }
    public void StringToDict(){
        this.attrIdList = new ArrayList<>();
        this.attrNameList = new ArrayList<>();
        this.providerList = new ArrayList<>();
        for (String attr :this.attrInfo.split("#")){
            String[] str = attr.split("|");
            this.attrIdList.add(Integer.parseInt(str[0]));
            this.attrNameList.add(str[1]);
            this.providerList.add(str[2]);
        }
    }
    public void dictToString(){
        int i=0;
        this.attrInfo="";
        for(;i<this.attrIdList.size()-1;i++){
            this.attrInfo += String.valueOf(this.attrIdList.get(i))+"|"+
                    this.attrNameList.get(i)+"|"+this.providerList.get(i)+"#";
        }
        i = this.attrIdList.size()-1;
        this.attrInfo += String.valueOf(this.attrIdList.get(i))+"|"+
                this.attrNameList.get(i)+"|"+this.providerList.get(i);
    }
}
