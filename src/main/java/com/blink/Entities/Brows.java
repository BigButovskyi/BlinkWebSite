package com.blink.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "Brows")
public class Brows{

    @Id
    @GeneratedValue
    private Long id_brows;
    private Date date;
    private Time time;
    private Long id_client;

    public Long getId_brows() {
        return id_brows;
    }

    public void setId_brows(Long id_brows) {
        this.id_brows = id_brows;
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