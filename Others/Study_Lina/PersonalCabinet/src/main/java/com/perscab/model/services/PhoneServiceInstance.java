package com.perscab.model.services;


public class PhoneServiceInstance extends ServiceInstance {

    private int talkLimit;
    private int dataLimit;
    private boolean voiceMail;

    public PhoneServiceInstance(String type) {
        super(type);
    }

    public PhoneServiceInstance(String name, String type, String status, double price) {
        super(name, type, status, price);
    }

    public int getTalkLimit() {
        return talkLimit;
    }

    public void setTalkLimit(int talkLimit) {
        this.talkLimit = talkLimit;
    }

    public int getDataLimit() {
        return dataLimit;
    }

    public void setDataLimit(int dataLimit) {
        this.dataLimit = dataLimit;
    }

    public boolean isVoiceMail() {
        return voiceMail;
    }

    public void setVoiceMail(boolean voiceMail) {
        this.voiceMail = voiceMail;
    }
}
