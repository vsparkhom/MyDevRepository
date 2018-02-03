package com.perscab.model.services;


import com.perscab.model.services.options.PhoneOptions;

public class PhoneServicePlan extends ServicePlan implements PhoneOptions {

    public PhoneServicePlan(ServiceType type) {
        super(type);
    }

    public PhoneServicePlan(long id, String name, ServiceType type, double price) {
        super(id, name, type, price);
    }

    @Override
    public int getTalkLimit() {
        return (int) getOptions().get("talkLimit");
    }

    @Override
    public void setTalkLimit(int talkLimit) {
        getOptions().put("talkLimit", talkLimit);
    }

    @Override
    public int getDataLimit() {
        return (int) getOptions().get("dataLimit");
    }

    @Override
    public void setDataLimit(int dataLimit) {
        getOptions().put("dataLimit", dataLimit);
    }

    @Override
    public boolean isVoiceMail() {
        return (boolean) getOptions().get("voiceMail");
    }

    @Override
    public void setVoiceMail(boolean voiceMail) {
        getOptions().put("voiceMail", voiceMail);
    }
}
