package com.perscab.model.services;

public class ServiceInstance {

    private String name;
    private String type;
    private String status;
    private double price;

    public ServiceInstance(String type) {
        this("", type, "Inactive", 0);
    }

    public ServiceInstance(String name, String type, String status, double price) {
        this.name = name;
        this.type = type;
        this.status = status;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ServiceInstance{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
