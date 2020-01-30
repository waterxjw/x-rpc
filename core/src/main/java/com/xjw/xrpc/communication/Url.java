package com.xjw.xrpc.communication;

public class Url {

    private String ip;

    private int port;

    public Url(String ip,int port){
        this.ip=ip;
        this.port=port;
    }
    public Url(){
        this("127.0.0.1",8081);
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

}
