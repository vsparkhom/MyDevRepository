package com.perscab.model;

import java.math.BigInteger;

public class Hardware {

    private long serviceTypeId;
    private String name;
    private String serialNumber;
    private String status;

    public Hardware(long serviceTypeId, String name, String serialNumber, String status) {
        this.serviceTypeId = serviceTypeId;
        this.name = name;
        this.serialNumber = serialNumber;
        this.status = status;
    }

    public long getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(long serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Hardware{" +
                "serviceTypeId=" + serviceTypeId +
                ", name='" + name + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}