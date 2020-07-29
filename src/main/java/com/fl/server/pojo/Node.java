package com.fl.server.pojo;

public class Node {
    private int id;

    private String nodeName;
    private String ipAddress;
    private String port;
    private String csvPath;
    private String logo;
    private boolean nodeStatus;

    public Node(String nodeName, String ipAddress, String port, String csvPath, String logo, boolean nodeStatus) {
        this.nodeName = nodeName;
        this.ipAddress = ipAddress;
        this.port = port;
        this.csvPath = csvPath;
        this.logo = logo;
        this.nodeStatus = nodeStatus;
    }

    public boolean getNodeStatus() {
        return nodeStatus;
    }

    public void setNodeStatus(boolean nodeStatus) {
        this.nodeStatus = nodeStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getCsvPath() {
        return csvPath;
    }

    public void setCsvPath(String csvPath) {
        this.csvPath = csvPath;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
