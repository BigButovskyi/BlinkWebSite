package com.blink.Entities;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
public class Reservation {
    @Id
    @GeneratedValue
    private Long id_reservation;
    private Date date;
    private Time time;
    private String service;

    public Long getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(Long id_reservation) {
        this.id_reservation = id_reservation;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

//    public Long getId_client() {
//        return id_client;
//    }
//
//    public void setId_client(Long id_client) {
//        this.id_client = id_client;
//    }
}
