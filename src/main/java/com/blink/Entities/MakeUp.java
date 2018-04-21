package com.blink.Entities;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "MakeUp")
public class MakeUp {

    @Id
    @GeneratedValue
    private Long id_makeUp;
    private Date date;
    private Time time;
    private Long id_client;

    public Long getId_makeUp() {
        return id_makeUp;
    }

    public void setId_makeUp(Long id_makeUp) {
        this.id_makeUp = id_makeUp;
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