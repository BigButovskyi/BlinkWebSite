package com.blink.Entities;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;

@Entity
//@NamedQueries(@NamedQuery(name = "Clients.byID", query = "from Clients where id = ?"))
//@NamedNativeQuery(name = "Clients.byName", query = "select * from Clients where name = ?", resultClass = Client.class)
@Table(name = "Clients")
@JsonPropertyOrder({"phone", "email", "name"})
public class Client {
    @Id
    @GeneratedValue
    private Long id_client;
    private String phone;
    private String email;
    private String name;

//    @OneToMany(cascade = CascadeType.ALL)
//    private List<Reservation> reservations = new ArrayList<>();

//    public List<Reservation> getReservations() {
//        return reservations;
//    }

//    public void setReservations(List<Reservation> reservations) {
//        this.reservations = reservations;
//    }

    public Long getId_client() {
        return id_client;
    }

    public void setId_client(Long id_client) {
        this.id_client = id_client;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}