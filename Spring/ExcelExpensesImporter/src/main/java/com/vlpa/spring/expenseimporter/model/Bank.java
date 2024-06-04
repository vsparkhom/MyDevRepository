package com.vlpa.spring.expenseimporter.model;

public enum Bank {

    //TODO: add other banks
    TD("TD"),
    PCF("PCF");

    private String value;

    Bank(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Bank resolveBank(String value) {
        for (Bank bank : values()) {
            if (bank.getValue().equalsIgnoreCase(value)) {
                return bank;
            }
        }
        throw new RuntimeException(String.format("Bank '%s' is not supported"));
    }
}
