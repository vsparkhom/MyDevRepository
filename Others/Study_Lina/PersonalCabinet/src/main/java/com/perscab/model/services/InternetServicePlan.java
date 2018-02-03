package com.perscab.model.services;

import com.perscab.model.services.options.InternetOptions;

public class InternetServicePlan extends ServicePlan implements InternetOptions {

    public InternetServicePlan(ServiceType type) {
        super(type);
    }

    public InternetServicePlan(long id, String name, ServiceType type, double price) {
        super(id, name, type, price);
    }

    @Override
    public String getDownloadSpeed() {
        return (String) getOptions().get("downloadSpeed");
    }

    @Override
    public void setDownloadSpeed(String downloadSpeed) {
        getOptions().put("downloadSpeed", downloadSpeed);
    }

    @Override
    public String getUploadSpeed() {
        return (String) getOptions().get("uploadSpeed");
    }

    @Override
    public void setUploadSpeed(String uploadSpeed) {
        getOptions().put("uploadSpeed", uploadSpeed);
    }

    @Override
    public String getDataLimit() {
        return (String) getOptions().get("dataLimit");
    }

    @Override
    public void setDataLimit(String dataLimit) {
    }

}


