package com.perscab.model.support;

import java.math.BigInteger;

public class SupportSocialInfo extends SupportInfo {

    private BigInteger attrId;
    private String name;
    private String link;

    public SupportSocialInfo() {}

    public SupportSocialInfo(BigInteger categoryId, String categoryName, BigInteger attrId, String name, String link) {
        super(categoryId, categoryName);
        this.attrId = attrId;
        this.name = name;
        this.link = link;
    }

    public BigInteger getAttrId() {
        return attrId;
    }

    public void setAttrId(BigInteger attrId) {
        this.attrId = attrId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "SupportSocialInfo{" +
                "category=[" + super.toString() + ']' +
                "attrId='" + attrId + '\'' +
                "name='" + name + '\'' +
                "link='" + link + '\'' +
                '}';
    }
}
