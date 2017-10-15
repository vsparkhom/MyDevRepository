package com.vlpa.jaxb.inventory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "id",
        "serialNumber",
        "provisioningRegistrationKey",
        "manufacturer",
        "modelType",
        "docsisMacAddress",
        "isProvisionable",
        "description",
        "memo",
        "isRental",
        "proprietor",
        "components",
        "specifications",
        "productLines",
        "state",
        "isActive",
        "lastModifiedDate",
        "link"
})
public class Equipment {

    @XmlElement
    protected int id;

    @XmlElement(required = true)
    protected String serialNumber;

    @XmlElement(required = true)
    protected String provisioningRegistrationKey;

    @XmlElement(required = true)
    protected String manufacturer;

    @XmlElement(required = true)
    protected String modelType;

    @XmlElement(required = true)
    protected String docsisMacAddress;

    @XmlElement(required = true)
    protected String isProvisionable;

    @XmlElement(required = true)
    protected String description;

    @XmlElement(required = true)
    protected String memo;

    @XmlElement(required = true)
    protected String isRental;

    @XmlElement(required = true)
    protected Proprietor proprietor;

    @XmlElement(required = true)
    protected Components components;

    @XmlElement(required = true)
    protected Specifications specifications;

    @XmlElement(required = true)
    protected ProductLines productLines;

    @XmlElement(required = true)
    protected String state;

    @XmlElement(required = true)
    protected String isActive;

    @XmlElement(required = true)
    protected String lastModifiedDate;

    @XmlElement
    protected List<Link> link;

    @XmlAttribute(name = "type")
    protected String type;

    public Equipment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int value) {
        this.id = value;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String value) {
        this.serialNumber = value;
    }

    public String getProvisioningRegistrationKey() {
        return provisioningRegistrationKey;
    }

    public void setProvisioningRegistrationKey(String value) {
        this.provisioningRegistrationKey = value;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String value) {
        this.manufacturer = value;
    }

    public String getModelType() {
        return modelType;
    }

    public void setModelType(String value) {
        this.modelType = value;
    }

    public String getDocsisMacAddress() {
        return docsisMacAddress;
    }

    public void setDocsisMacAddress(String value) {
        this.docsisMacAddress = value;
    }

    public String getIsProvisionable() {
        return isProvisionable;
    }

    public void setIsProvisionable(String value) {
        this.isProvisionable = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String value) {
        this.memo = value;
    }

    public String getIsRental() {
        return isRental;
    }

    public void setIsRental(String value) {
        this.isRental = value;
    }

    public Proprietor getProprietor() {
        return proprietor;
    }

    public void setProprietor(Proprietor value) {
        this.proprietor = value;
    }

    public Components getComponents() {
        return components;
    }

    public void setComponents(Components value) {
        this.components = value;
    }

    public Specifications getSpecifications() {
        return specifications;
    }

    public void setSpecifications(Specifications value) {
        this.specifications = value;
    }

    public ProductLines getProductLines() {
        return productLines;
    }

    public void setProductLines(ProductLines value) {
        this.productLines = value;
    }

    public String getState() {
        return state;
    }

    public void setState(String value) {
        this.state = value;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String value) {
        this.isActive = value;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String value) {
        this.lastModifiedDate = value;
    }

    public List<Link> getLink() {
        if (link == null) {
            link = new ArrayList<Link>();
        }
        return this.link;
    }

    public String getType() {
        return type;
    }

    public void setType(String value) {
        this.type = value;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", serialNumber='" + serialNumber + '\'' +
                ", provisioningRegistrationKey='" + provisioningRegistrationKey + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", modelType='" + modelType + '\'' +
                ", docsisMacAddress='" + docsisMacAddress + '\'' +
                ", isProvisionable='" + isProvisionable + '\'' +
                ", description='" + description + '\'' +
                ", memo='" + memo + '\'' +
                ", isRental='" + isRental + '\'' +
//                ", proprietor=" + proprietor +
//                ", components=" + components +
//                ", specifications=" + specifications +
//                ", productLines=" + productLines +
                ", state='" + state + '\'' +
                ", isActive='" + isActive + '\'' +
                ", lastModifiedDate='" + lastModifiedDate + '\'' +
//                ", link=" + link +
                ", type='" + type + '\'' +
                '}';
    }
}