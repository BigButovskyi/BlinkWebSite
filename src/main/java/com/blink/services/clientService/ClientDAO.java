package com.blink.services.clientService;

import com.blink.Entities.Client;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.sql.Date;

@Repository
public class ClientDAO implements ClientDAOInterface {

    @PersistenceContext
    private EntityManager entityManager;

    //adding new client
    public void addClient(Client client) {
        //checking If client is already registered in DataBase
        if (clientIsInBaseByEmail(client.getEmail()) == 0) {
            entityManager.persist(client);
        }
    }

    //Get id_client by email
    public long getIdClientByEmail(String email) {
        if (clientIsInBaseByEmail(email) != 0) {
            String query = "SELECT id_client FROM Clients where email = '" + email + "'";
            Object id = entityManager.createNativeQuery(query).getSingleResult();
            int id_client = (Integer) id;
            return id_client;
        }
        return 0;
    }

    public long getIdClientByPhone(String phone) {
        if (clientIsInBaseByPhone(phone) != 0) {
            String query = "SELECT id_client FROM Clients where phone = '" + phone + "'";
            Object id = entityManager.createNativeQuery(query).getSingleResult();
            int id_client = (Integer) id;
            return id_client;
        }
        return 0;
    }

    public void addEmailAndCode(String email, int code){
        java.util.Date utilDate = new java.util.Date();
        Date date = new java.sql.Date(utilDate.getTime());
        if(emailIsExist(email) == 0){
            String sql = "insert into Validation(email, code, date) VALUES " +
                    "('" + email + "', " + code + ", '"+ date + "')";
            Query query = entityManager.createNativeQuery(sql);
            query.executeUpdate();
        }else{
            String sql = "update Validation " +
                    "set code = " + code +" " +
                    "where email = '" + email + "'";
            Query query = entityManager.createNativeQuery(sql);
            query.executeUpdate();
        }
    }

    @Override
    public boolean checkCode(String email, int code){
        String sql = "SELECT count(*)from Validation where email = '" + email + "' AND code = " + code;
        int number = ((BigInteger) entityManager.createNativeQuery(sql).getSingleResult()).intValue();
        if(number == 0)
            return false;
        sql = "delete from Validation where email = '" + email + "'";
        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();

        cleanValidationTable();
        return true;
    }

    @Override
    public Client getClient(long id_client) {
        return entityManager.find(Client.class, id_client);
    }

    private void cleanValidationTable(){
        java.util.Date utilDate = new java.util.Date();
        Date date = new java.sql.Date(utilDate.getTime());
        String sql = "delete from Validation where date < '" + date + "'";
        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
    }

    private int emailIsExist(String email) {
        String query = "SELECT count(*)from Validation where email = '" + email + "'";
        int number = ((BigInteger) entityManager.createNativeQuery(query).getSingleResult()).intValue();
        return number;
    }

    //check by email if client is in base
    private int clientIsInBaseByEmail(String email) {
        String query = "SELECT count(*)from Clients where email = '" + email + "'";
        int number = ((BigInteger) entityManager.createNativeQuery(query).getSingleResult()).intValue();
        return number;
    }


    //check by phone if client is in base
    private int clientIsInBaseByPhone(String phone) {
        String query = "SELECT count(*)from Clients where phone = '" + phone + "'";
        int number = ((BigInteger) entityManager.createNativeQuery(query).getSingleResult()).intValue();
        return number;
    }

}
