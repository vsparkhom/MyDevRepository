package com.perscab.model.services;

public class InternetServiceInstance extends ServiceInstance {

    private String downloadSpeed;
    private String uploadSpeed;
    private String dataLimit;

    public InternetServiceInstance(String name, String type, String status, double price) {
        super(name, type, status, price);
    }

    public String getDownloadSpeed() {
        return downloadSpeed;
    }

    public void setDownloadSpeed(String downloadSpeed) {
        this.downloadSpeed = downloadSpeed;
    }

    public String getUploadSpeed() {
        return uploadSpeed;
    }

    public void setUploadSpeed(String uploadSpeed) {
        this.uploadSpeed = uploadSpeed;
    }

    public String getDataLimit() {
        return dataLimit;
    }

    public void setDataLimit(String dataLimit) {
        this.dataLimit = dataLimit;
    }

    @Override
    public String toString() {
        return "InternetServiceInstance{" +
                "serviceInstance=[" + super.toString() + ']' +
                "downloadSpeed='" + downloadSpeed + '\'' +
                ", uploadSpeed='" + uploadSpeed + '\'' +
                ", dataLimit='" + dataLimit + '\'' +
                '}';
    }
}
