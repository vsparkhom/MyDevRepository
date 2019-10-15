package com.perscab.model.services.options;

public interface InternetOptions {

    int getDownloadSpeed();

    void setDownloadSpeed(int downloadSpeed);

    int getUploadSpeed();

    void setUploadSpeed(int uploadSpeed);

    int getDataLimit();

    void setDataLimit(int dataLimit);
}
