package com.perscab.model.services;

public class ServicePlan extends Service {

    private double price;

    public ServicePlan(ServiceType type) {
        super(type);
    }

    public ServicePlan(long id, String name, ServiceType type, double price) {
        super(id, name, type);
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ServicePlan{" +
                "service=[" + super.toString() + ']' +
                "price=" + price +
                '}';
    }
}
