package com.fl.server.pojo;

import java.util.ArrayList;

public class Scene {
    private int id;
    private String institution;
    private String sceneName;
    private String target;
    private String description;
    private ArrayList<Integer> valueList;
    private ArrayList<String> keyList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Integer> getValueList() {
        return valueList;
    }

    public void setValueList(ArrayList<Integer> valueList) {
        this.valueList = valueList;
    }

    public ArrayList<String> getKeyList() {
        return keyList;
    }

    public void setKeyList(ArrayList<String> keyList) {
        this.keyList = keyList;
    }

    public void StringToDict(){
        this.keyList = new ArrayList<>();
        this.valueList = new ArrayList<>();
        for (String attr :this.description.split("#")){
            String[] str = attr.split(":");
            this.valueList.add(Integer.parseInt(str[0]));
            this.keyList.add(str[1]);
        }
    }

    public void dictToString(){
        int i=0;
        this.description="";
        for(; i < (this.keyList.size() - 1); i++){
            this.description += String.valueOf(this.valueList.get(i))+":"+
                    this.keyList.get(i)+"#";
        }
        i = this.keyList.size()-1;
        this.description += String.valueOf(this.valueList.get(i))+":"+
                this.keyList.get(i);
    }

}
