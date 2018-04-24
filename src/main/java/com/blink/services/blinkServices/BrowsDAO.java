package com.blink.services.blinkServices;

import com.blink.Entities.Brows;
import com.blink.Entities.MakeUp;
import com.blink.Entities.Nails;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BrowsDAO implements BrowsDAOInterface {

    @PersistenceContext
    private EntityManager entityManager;

    //Adding new nails service
    @Override
    public void addService(Date date, Time time, long id_client){
        Brows brows = new Brows();
        brows.setDate(date);
        brows.setTime(time);
        brows.setId_client(id_client);
        entityManager.persist(brows);
    }

    @Override
    public Object[] getNailsReservationsByClientId(long id_client) {
        Query query = entityManager.createNativeQuery("select * from Brows where id_client = " + id_client + "", Brows.class);
        List<Brows> list = query.getResultList();
        Object[] array = new Object[list.size()];

        for (int i = 0; i < list.size(); i++) {
            Map<String, String> temporaryMap = new HashMap<>();
            Brows temporaryBrows = list.get(i);
            temporaryMap.put("date", temporaryBrows.getDate().toString());
            temporaryMap.put("time", temporaryBrows.getTime().toString());
            array[i] = temporaryMap;
        }

        return array;
    }

    @Override
    public List<Time> getBusyTimesforService(Date date) {
        String sql = "select a.time from Masters, " +
                "(select count(*) as counter, time from Brows where date = '" + date + "' group by time) a " +
                "where (Masters.service='Brows') and (a.counter=Masters.max)";
        Query query = entityManager.createNativeQuery(sql);
        List<Time> list = query.getResultList();
        return list;
    }

    @Override
    public void removeReservation(Date date, Time time, long id_client) {
        String sql = "delete from Brows where date = '" + date +
                "' AND time = '" + time + "' AND id_client = " + id_client;
        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
    }

    @Override
    public void updateService(long id_client, Date old_date, Time old_time, Date new_date, Time new_time){
        String sql = "update Brows " +
                "set date = '" + new_date + "', time = '" + new_time + "'"+
                "where date = '" + old_date + "' AND time = '" + old_time + "' AND id_client = "+ id_client;
        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
    }


    private int browsIsInBase(Date date, Time time, long id_client) {
        String query = "SELECT count(*)from Brows where date = '" + date +
                "' AND time = '" + time + "' AND id_client = " + id_client;
        int number = ((BigInteger) entityManager.createNativeQuery(query).getSingleResult()).intValue();
        System.out.println(number);
        return number;
    }
}