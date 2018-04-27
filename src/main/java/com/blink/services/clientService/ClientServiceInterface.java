package com.blink.services.clientService;

import com.blink.Entities.Client;

public interface ClientServiceInterface {
    void addClient(Client client);
    long getIdClientByEmail(String email);
    long getIdClientByPhone(String phone);
    void addEmailAndCode(String email, int code);
    boolean checkCode(String email, int code);

    Client getClient(long id_client);
}
