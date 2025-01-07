package com.vlpa.spring.expenseimporter.model;

public enum Bank {

    TD,
    PCF,
    CIBC;

    public static Bank resolveBank(String value) {
        for (Bank bank : values()) {
            if (bank.name().equalsIgnoreCase(value)) {
                return bank;
            }
        }
        throw new RuntimeException(String.format("Bank '%s' is not supported", value));
    }
}
