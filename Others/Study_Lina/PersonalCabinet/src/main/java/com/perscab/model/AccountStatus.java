package com.perscab.model;

public enum AccountStatus {

    Active("active"), Inactive("inactive");

    private String value;

    AccountStatus(String value) {
        this.value = value;
    }

    public static AccountStatus getAccountStatusByValue(String value) {
        for(AccountStatus as : values()) {
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
