package com.perscab.model.services;

public class ServiceOptionKey {

    private String id;
    private boolean i18n;

    public ServiceOptionKey(String id) {
        this.id = id;
    }

    public ServiceOptionKey(String id, boolean i18n) {
        this.id = id;
        this.i18n = i18n;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isI18n() {
        return i18n;
    }

    public void setI18n(boolean i18n) {
        this.i18n = i18n;
    }
}
