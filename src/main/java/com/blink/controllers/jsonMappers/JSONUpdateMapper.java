package com.blink.controllers.jsonMappers;

public class JSONUpdateMapper {
    private String email;
    private String service;
    private String old_date;
    private String old_time;
    private String new_date;
    private String new_time;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getOld_date() {
        return old_date;
    }

    public void setOld_date(String old_date) {
        this.old_date = old_date;
    }

    public String getOld_time() {
        return old_time;
    }

    public void setOld_time(String old_time) {
        this.old_time = old_time;
    }

    public String getNew_date() {
        return new_date;
    }

    public void setNew_date(String new_date) {
        this.new_date = new_date;
    }

    public String getNew_time() {
        return new_time;
    }

    public void setNew_time(String new_time) {
        this.new_time = new_time;
    }
}
