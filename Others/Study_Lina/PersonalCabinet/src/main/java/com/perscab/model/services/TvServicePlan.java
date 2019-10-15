package com.perscab.model.services;

import com.perscab.model.services.options.TvOptions;
import static com.perscab.model.services.TvServicePlan.TvServiceOptionsEnum.*;

public class TvServicePlan extends ServicePlan implements TvOptions {

    public TvServicePlan(ServiceType type) {
        super(type);
    }

    public TvServicePlan(long id, String name, ServiceType type, double price) {
        super(id, name, type, price);
    }

    public int getChannelsCount() {
        return (int) getOptions().get(CHANNELS_COUNT.getOption());
    }

    public void setChannelsCount(int channelsCount) {
        getOptions().put(CHANNELS_COUNT.getOption(), channelsCount);
    }

    public boolean getUhdSupport() {
        return (boolean) getOptions().get(UHD_SUPPORT.getOption());
    }

    public void setUhdSupport(boolean uhdSupport) {
        getOptions().put(UHD_SUPPORT.getOption(), uhdSupport);
    }

    public enum TvServiceOptionsEnum {

//        CHANNELS_COUNT(new ServiceOptionKey("channelsCount")),
//        UHD_SUPPORT(new ServiceOptionKey("uhdSupport", true));
        CHANNELS_COUNT(new ServiceOptionKey("db.service_opts.tv.channels_count")),
        UHD_SUPPORT(new ServiceOptionKey("db.service_opts.tv.uhd_support", true));

        private ServiceOptionKey option;

        TvServiceOptionsEnum(ServiceOptionKey option) {
            this.option = option;
        }

        public ServiceOptionKey getOption() {
            return option;
        }
    }
}
