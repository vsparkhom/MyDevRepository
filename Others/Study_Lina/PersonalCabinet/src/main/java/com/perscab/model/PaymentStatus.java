package com.perscab.model;

public enum PaymentStatus {

    Paid("paid"), Not_Paid("not_paid");

    private String value;

    PaymentStatus(String value) {
        this.value = value;
    }

    public static PaymentStatus getPaymentStatusByValue(String value) {
        for(PaymentStatus ps : values()) {
            if (ps.value.equals(value)) {
                return ps;
            }
        }
        return Not_Paid;
    }

    public String getValue() {
        return value;
    }
}