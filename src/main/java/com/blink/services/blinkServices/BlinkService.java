package com.blink.services.blinkServices;

import com.blink.Entities.Brows;
import com.blink.Entities.MakeUp;
import com.blink.Entities.Nails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Time;
import java.util.HashMap;
import java.util.Map;

@Component
@Transactional
public class BlinkService implements BlinkServiceInterface{

    @Autowired
    private NailsDAOInterface nailsDAO;
    @Autowired
    private BrowsDAOInterface browsDAO;
    @Autowired
    private MakeUpDAOInterface makeUpDAO;

    @Override
    public void addService(String service, Date date, Time time, long id_client) {
        if(service.equals("Nails") || service.equals("nails")){
            Nails nails = new Nails();
            nails.setDate(date);
            nails.setTime(time);
            nails.setId_client(id_client);
            nailsDAO.addService(nails);
        }else if (service.equals("Brows") || service.equals("brows") || service.equals("hair") || service.equals("Hair")){
            Brows brows = new Brows();
            brows.setDate(date);
            brows.setTime(time);
            brows.setId_client(id_client);
            browsDAO.addService(brows);
        }else{
            MakeUp makeUp = new MakeUp();
            makeUp.setDate(date);
            makeUp.setTime(time);
            makeUp.setId_client(id_client);
            makeUpDAO.addService(makeUp);
        }
    }

    @Override
    public Map<String, Object[]> getClientReservations(long id_client){
        Map<String, Object[]> map = new HashMap<>();
        map.put("makeUp", makeUpDAO.getNailsReservationsByClientId(id_client));
        map.put("brows", browsDAO.getNailsReservationsByClientId(id_client));
        map.put("nails", nailsDAO.getNailsReservationsByClientId(id_client));
        return map;
    }
}
