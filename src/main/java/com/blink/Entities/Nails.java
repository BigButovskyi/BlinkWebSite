package com.blink.Entities;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "Nails")
public class Nails {

    @Id
    @GeneratedValue
    private Long id_nails;
    private Date date;
    private Time time;
    private Long id_client;

    public Long getId_nails() {
        return id_nails;
    }

    public void setId_nails(Long id_nails) {
        this.id_nails = id_nails;
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

    public Long getId_client() {
        return id_client;
    }

    public void setId_client(Long id_client) {
        this.id_client = id_client;
    }
}
