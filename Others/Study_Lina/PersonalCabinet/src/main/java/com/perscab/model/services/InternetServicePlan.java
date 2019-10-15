package com.perscab.model.services;

import com.perscab.model.services.options.InternetOptions;
import static com.perscab.model.services.InternetServicePlan.InternetServiceOptionsEnum.*;

public class InternetServicePlan extends ServicePlan implements InternetOptions {

    public InternetServicePlan(ServiceType type) {
        super(type);
    }

    public InternetServicePlan(long id, String name, ServiceType type, double price) {
        super(id, name, type, price);
    }

    @Override
    public int getDownloadSpeed() {
        return (int) getOptions().get(DOWNLOAD_SPEED.getOption());
    }

    @Override
    public void setDownloadSpeed(int downloadSpeed) {
        getOptions().put(DOWNLOAD_SPEED.getOption(), downloadSpeed);
    }

    @Override
    public int getUploadSpeed() {
        return (int) getOptions().get(UPLOAD_SPEED.getOption());
    }

    @Override
    public void setUploadSpeed(int uploadSpeed) {
        getOptions().put(UPLOAD_SPEED.getOption(), uploadSpeed);
    }

    @Override
    public int getDataLimit() {
        return (int) getOptions().get(DATA_LIMIT.getOption());
    }

    @Override
    public void setDataLimit(int dataLimit) {
        getOptions().put(DATA_LIMIT.getOption(), dataLimit);
    }

    public enum InternetServiceOptionsEnum {

//        DOWNLOAD_SPEED(new ServiceOptionKey("downloadSpeed")),
//        UPLOAD_SPEED(new ServiceOptionKey("uploadSpeed")),
//        DATA_LIMIT(new ServiceOptionKey("dataLimit"));
        DOWNLOAD_SPEED(new ServiceOptionKey("db.service_opts.internet.download_speed")),
        UPLOAD_SPEED(new ServiceOptionKey("db.service_opts.internet.upload_speed")),
        DATA_LIMIT(new ServiceOptionKey("db.service_opts.internet.data_limit"));

        private ServiceOptionKey option;

        InternetServiceOptionsEnum(ServiceOptionKey option) {
            this.option = option;
        }

        public ServiceOptionKey getOption() {
            return option;
        }
    }
}


