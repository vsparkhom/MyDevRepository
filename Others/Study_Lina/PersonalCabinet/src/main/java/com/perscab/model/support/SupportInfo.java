package com.perscab.model.support;

import java.math.BigInteger;

public class SupportInfo {

    private BigInteger categoryId;
    private String categoryName;

    public SupportInfo() {}

    public SupportInfo(BigInteger categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public BigInteger getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(BigInteger categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "SupportInfo{" +
                "categoryName='" + categoryName + '\'' +
                "categoryId='" + categoryId + '\'' +
                '}';
    }
}
