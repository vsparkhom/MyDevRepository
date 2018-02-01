package com.perscab.model.support;

import java.math.BigInteger;

public class SupportServiceInfo extends SupportInfo {

    private String phoneNumber;
    private String email;

    public SupportServiceInfo() {}

    public SupportServiceInfo(BigInteger categoryId, String categoryName, String phoneNumber, String email) {
        super(categoryId, categoryName);
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "SupportServiceInfo{" +
                "category=[" + super.toString() + ']' +
                "phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
