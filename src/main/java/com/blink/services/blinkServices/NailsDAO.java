package com.blink.services.blinkServices;

import com.blink.Entities.Nails;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Date;
import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class NailsDAO implements NailsDAOInterface{

    @PersistenceContext
    private EntityManager entityManager;

    //Adding new nails service
    public void addService(Nails nails) {
        entityManager.persist(nails);
    }

    @Override
    public Object[] getNailsReservationsByClientId(long id_client) {
        Query query = entityManager.createNativeQuery("select * from Nails where id_client = " + id_client + "", Nails.class);
        List<Nails> list = query.getResultList();
        Object[] array = new Object[list.size()];

        for (int i = 0; i < list.size(); i++) {
            Map<String, String> temporaryMap = new HashMap<>();
            Nails temporaryNail = list.get(i);
            temporaryMap.put("date", temporaryNail.getDate().toString());
            temporaryMap.put("time", temporaryNail.getTime().toString());
            array[i] = temporaryMap;
        }

        return array;
    }

    @Override
    public List<Time> getBusyTimesforService(Date date) {
        String sql = "select a.time from Masters, " +
                "(select count(*) as counter, time from Nails where date = '" + date + "' group by time) a " +
                "where (Masters.service='nails') and (a.counter=Masters.max)";
        Query query = entityManager.createNativeQuery(sql);
        List<Time> list = query.getResultList();
        return list;
    }


}
