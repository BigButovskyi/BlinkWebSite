package com.blink.services.clientService;


import com.blink.Entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Map;

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

    public long getIdClientByEmail(String email){
        return clientDAO.getIdClientByEmail(email);
    }

    public long getIdClientByPhone(String phone){
        return clientDAO.getIdClientByPhone(phone);
    }

}
