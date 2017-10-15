package com.vlpa.jaxb.inventory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class Components {

    @XmlElement(required = true)
    protected String component;

    public Components() {
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String value) {
        this.component = value;
    }
}