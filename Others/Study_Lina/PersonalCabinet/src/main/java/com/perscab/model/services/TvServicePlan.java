package com.perscab.model.services;

import com.perscab.model.services.options.TvOptions;

public class TvServicePlan extends ServicePlan implements TvOptions {

    public TvServicePlan(ServiceType type) {
        super(type);
    }

    public TvServicePlan(long id, String name, ServiceType type, double price) {
        super(id, name, type, price);
    }

    public int getChannelsCount() {
        return (int) getOptions().get("channelsCount");
    }

    public void setChannelsCount(int channelsCount) {
        getOptions().put("channelsCount", channelsCount);
    }

    public String getUhdSupport() {
        return (String) getOptions().get("uhdSupport");
    }

    public void setUhdSupport(String uhdSupport) {
        getOptions().put("uhdSupport", uhdSupport);
    }
}
