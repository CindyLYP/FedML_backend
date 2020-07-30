package com.fl.server.pojo;

import com.fl.server.object.tools.TypeFactory;

import java.util.ArrayList;
import java.util.HashMap;

public class Scene {
    private int id;
    private String institution;
    private String sceneName;
    private String target;
    private String description;
    private ArrayList<HashMap<String, Object>> descriptionList;

    public Scene(){}

    public Scene(String institution, String sceneName, String target, ArrayList<HashMap<String, Object>> descriptionList) {
        this.institution = institution;
        this.sceneName = sceneName;
        this.target = target;
        this.descriptionList = descriptionList;
    }

    public ArrayList<HashMap<String, Object>> getDescriptionList() {
        return descriptionList;
    }

    public void setDescriptionList(ArrayList<HashMap<String, Object>> descriptionList) {
        this.descriptionList = descriptionList;
    }

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

    public void StringToDict(){
        this.descriptionList = new ArrayList<HashMap<String, Object>>();
        for (String attr :this.description.split("#")){
            String[] str = attr.split(":");

            HashMap<String, Object> pair = TypeFactory.GenerateHMSO();
            pair.put("value", str[0]);
            pair.put("label", str[1]);

            this.descriptionList.add(pair);
        }
    }

    public void dictToString(){
        this.description = "";

        StringBuilder description = new StringBuilder();
        for (HashMap<String, Object> pair: this.descriptionList){
            description.append(pair.get("value")).append(":").append(pair.get("label")).append("#");
        }
        description.deleteCharAt(description.length()-1);
        this.description = description.toString();
    }

}
