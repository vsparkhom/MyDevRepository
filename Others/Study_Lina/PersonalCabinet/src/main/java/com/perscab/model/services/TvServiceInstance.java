package com.perscab.model.services;


public class TvServiceInstance extends ServiceInstance {

    private int channelsCount;
    private String uhdSupport;

    public TvServiceInstance(String type) {
        super(type);
    }

    public TvServiceInstance(String name, String type, String status, double price) {
        super(name, type, status, price);
    }

    public int getChannelsCount() {
        return channelsCount;
    }

    public void setChannelsCount(int channelsCount) {
        this.channelsCount = channelsCount;
    }

    public String getUhdSupport() {
        return uhdSupport;
    }

    public void setUhdSupport(String uhdSupport) {
        this.uhdSupport = uhdSupport;
    }
}
