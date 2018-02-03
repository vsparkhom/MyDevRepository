package com.perscab.model.services.options;

public interface InternetOptions {

    String getDownloadSpeed();

    void setDownloadSpeed(String downloadSpeed);

    String getUploadSpeed();

    void setUploadSpeed(String uploadSpeed);

    String getDataLimit();

    void setDataLimit(String dataLimit);
}
