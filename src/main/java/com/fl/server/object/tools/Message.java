package com.fl.server.object.tools;

public class Message {
    private String message;
    private boolean state;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public void set(boolean state, String message) {
        this.state = state;
        this.message = message;
    }
}
