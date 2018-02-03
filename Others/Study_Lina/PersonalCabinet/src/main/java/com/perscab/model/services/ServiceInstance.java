package com.perscab.model.services;

public class ServiceInstance {

    private ServicePlan plan;
    private String status;

    public ServiceInstance(ServiceType type) {
        this(new ServicePlan(0, "", type, 0), "Inactive");
    }

    public ServiceInstance(ServicePlan plan, String status) {
        this.plan = plan;
        this.status = status;
    }

    public ServicePlan getPlan() {
        return plan;
    }

    public void setPlan(ServicePlan plan) {
        this.plan = plan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ServiceInstance{" +
                "plan=[" + super.toString() + ']' +
                ", status='" + status + '\'' +
                '}';
    }
}
