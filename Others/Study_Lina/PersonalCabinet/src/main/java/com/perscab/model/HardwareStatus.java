package com.perscab.model;

public enum HardwareStatus {

    Active("active"), Inactive("inactive");

    private String value;

    HardwareStatus(String value) {
        this.value = value;
    }

    public static HardwareStatus getAccountStatusByValue(String value) {
        for(HardwareStatus as : values()) {
            if (as.value.equals(value)) {
                return as;
            }
        }
        return Inactive;
    }

    public String getValue() {
        return value;
    }

}
