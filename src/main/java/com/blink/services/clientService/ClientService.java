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

    @Override
    public long getIdClientByEmail(String email){
        return clientDAO.getIdClientByEmail(email);
    }

    @Override
    public long getIdClientByPhone(String phone){
        return clientDAO.getIdClientByPhone(phone);
    }

    @Override
    public void addEmailAndCode(String email, int code){
            clientDAO.addEmailAndCode(email, code);
    }

    @Override
    public boolean checkCode(String email, int code){
        if (code < 1000 || code > 9999)
            return false;
        return clientDAO.checkCode(email,code);
    }

}
