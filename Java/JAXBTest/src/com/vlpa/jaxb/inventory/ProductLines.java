package com.vlpa.jaxb.inventory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class ProductLines {

    @XmlElement(required = true)
    protected String productLine;

    public ProductLines() {
    }

    public String getProductLine() {
        return productLine;
    }

    public void setProductLine(String value) {
        this.productLine = value;
    }
}