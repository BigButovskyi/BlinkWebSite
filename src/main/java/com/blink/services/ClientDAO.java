package com.blink.services;

import com.blink.Entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Repository
public class ClientDAO implements ClientDAOInterface{

    @PersistenceContext
    private EntityManager entityManager;

    public void addClient(Client client){
        //check If client is already registrated in DataBase
        if(clientIsInBase(client.getEmail()) == 0){
            entityManager.persist(client);
        }
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
