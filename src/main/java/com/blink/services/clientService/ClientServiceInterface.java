package com.blink.services.clientService;

import com.blink.Entities.Client;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface ClientServiceInterface {
    void addClient(Client client);
    List<Time> getBusyTimesforService(String service, Date date);
    long getIdClientByEmail(String email);
}
