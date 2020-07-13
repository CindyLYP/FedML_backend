package com.fl.server.object;

public class Clients {
    private String clientName;
    private String address;
    private int httpPort;
    private int computationPort;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getHttpPort() {
        return httpPort;
    }

    public void setHttpPort(int httpPort) {
        this.httpPort = httpPort;
    }

    public int getComputationPort() {
        return computationPort;
    }

    public void setComputationPort(int computationPort) {
        this.computationPort = computationPort;
    }
}
