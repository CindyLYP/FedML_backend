package com.fl.server.pojo;

public class User {
    private int id;
    private String userAccount;
    private String username;
    private String password;
    private String userType;
    private String institution;
    private boolean survival;

    public User(){}

    public User(String userAccount, String username, String password, String userType, String institution, boolean survival) {
        this.userAccount = userAccount;
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.institution = institution;
        this.survival = survival;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public boolean getSurvival() {
        return survival;
    }

    public void setSurvival(boolean survival) {
        this.survival = survival;
    }
}
