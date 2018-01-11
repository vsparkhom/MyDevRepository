package com.perscab.model;

public enum AccountStatus {
    Active("Active"), Inactive("Inactive");

    private String value;

    private AccountStatus(String value) {
        this.value = value;
    }

    public static AccountStatus getAccountStatusByValue(String value) {
        for(AccountStatus as : values()) {
            if (as.value == value) {
                return as;
            }
        }
        return Inactive;
    }
}
