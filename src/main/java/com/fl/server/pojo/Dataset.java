package com.fl.server.pojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Dataset {
    private int id;
    private int userId;

    public Dataset(int userId, int sceneId, String datasetName, int alignedNum, String timestamp, ArrayList<HashMap<String, Object>> dict) {
        this.userId = userId;
        this.sceneId = sceneId;
        this.datasetName = datasetName;
        this.alignedNum = alignedNum;
        this.timestamp = timestamp;
        this.dict = dict;
    }

    private int sceneId;
    private String attrInfo;
    private String datasetName;
    private int alignedNum;
    private String timestamp;
    private ArrayList<HashMap<String,Object>> dict;

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

    public ArrayList<HashMap<String, Object>> getDict() {
        return dict;
    }

    public void setDict(ArrayList<HashMap<String, Object>> dict) {
        this.dict = dict;
    }

    public void StringToDict(){
        this.dict = new ArrayList<>();
        for(String items:this.attrInfo.split("#")){
            String[] item = items.split("\\|");
            HashMap<String,Object> node = new HashMap<>();
            node.put("provider",item[0]);
            node.put("attributes", Arrays.copyOfRange(item,1,item.length));
            this.dict.add(node);
        }
    }

    public void dictToString(){
        this.attrInfo="";
        for (HashMap<String,Object> node:this.dict){
            this.attrInfo += node.get("provider");
            for(String attr:(String[])node.get("attributes")){
                this.attrInfo += "|"+attr;
            }
            this.attrInfo +="#";
        }
        this.attrInfo = this.attrInfo.substring(0,this.attrInfo.length()-1);
    }
}
