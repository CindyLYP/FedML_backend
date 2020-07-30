package com.fl.server.pojo;

public class Scene {
    private int id;
    private String institution;
    private String sceneName;
    private String target;
    private String description;

    public Scene(){}

    public Scene(String institution, String sceneName, String target, String description) {
        this.institution = institution;
        this.sceneName = sceneName;
        this.target = target;
        this.description = description;
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
}
