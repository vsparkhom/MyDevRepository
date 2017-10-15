package com.vlpa.jaxb.inventory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class Specifications {

    @XmlElement(required = true)
    protected String specification;

    public Specifications() {
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String value) {
        this.specification = value;
    }
}