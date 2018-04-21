package com.blink.services.clientService;

import com.blink.Entities.Client;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Repository
public class ClientDAO implements ClientDAOInterface{

    @PersistenceContext
    private EntityManager entityManager;

    //adding new client
    public void addClient(Client client){
        //checking If client is already registered in DataBase
        if(clientIsInBase(client.getEmail()) == 0){
            entityManager.persist(client);
        }
    }

    //Get id_client by email
    public long getIdClientByEmail(String email){
        String query = "SELECT id_client FROM Clients where email = '" + email + "'";
        Object id = entityManager.createNativeQuery(query).getSingleResult();
        int id_client = (Integer) id;
        return id_client;
    }

    public List<Time> getBusyTimesforService(String service, Date date){
        List<Time> times = null;
//        Query query = entityManager.createQuery("select a.time from Masters, " +
//                "(select count(s) as counter, time from :SERVICE s where date = :DATE group by time a" +
//                "where (Masters.service= = :SERVICE) and (a.counter=Masters.max)");
//        query.setParameter("SERVICE", service);
//        query.setParameter("DATE", date);
//        times = query.getResultList();

        return times;
    }

    private int clientIsInBase(String email){
        String query = "SELECT count(*)from Clients where email = '" + email + "'";
        int number = ((BigInteger) entityManager.createNativeQuery(query).getSingleResult()).intValue();
        return number;
    }
}
