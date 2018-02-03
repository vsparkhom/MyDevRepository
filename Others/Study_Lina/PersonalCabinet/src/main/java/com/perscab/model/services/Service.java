package com.perscab.model.services;

import java.util.HashMap;
import java.util.Map;

public class Service {

    private long id;
    private String name;
    private ServiceType type;
    private Map<String, Object> options;

    public Service(ServiceType type) {
        this.type = type;
        this.options = new HashMap<>();
    }

    public Service(long id, String name, ServiceType type) {
        this(type);
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ServiceType getType() {
        return type;
    }

    public void setType(ServiceType type) {
        this.type = type;
    }

    public Map<String, Object> getOptions() {
        return options;
    }

    public void setOptions(Map<String, Object> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "Service{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", options=" + options +
                '}';
    }
}
