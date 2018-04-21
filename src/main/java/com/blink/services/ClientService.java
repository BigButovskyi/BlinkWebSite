package com.blink.services;


import com.blink.Entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Component
@Transactional
public class ClientService implements ClientServiceInterface{

    @Autowired
    private ClientDAOInterface clientDAO;

    @Override
    public void addClient(Client client) {
        clientDAO.addClient(client);
    }

    public List<Time> getBusyTimesforService(String service, Date date){
        if(service == null || date == null)
            return null;
        return clientDAO.getBusyTimesforService(service, date);
    }
}
