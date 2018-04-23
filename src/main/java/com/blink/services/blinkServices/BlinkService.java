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
import java.util.List;
import java.util.Map;

@Component
@Transactional
public class BlinkService implements BlinkServiceInterface {

    @Autowired
    private NailsDAOInterface nailsDAO;
    @Autowired
    private BrowsDAOInterface browsDAO;
    @Autowired
    private MakeUpDAOInterface makeUpDAO;

    @Override
    public void addService(String service, Date date, Time time, long id_client) {
        if (service.equals("Nails") || service.equals("nails")) {
            nailsDAO.addService(date, time, id_client);
        } else if (service.equals("Brows") || service.equals("brows") || service.equals("hair") || service.equals("Hair")) {
            browsDAO.addService(date, time, id_client);
        } else {
            makeUpDAO.addService(date, time, id_client);
        }
    }

    @Override
    public Map<String, Object[]> getClientReservations(long id_client) {
        Map<String, Object[]> map = new HashMap<>();
        map.put("makeUp", makeUpDAO.getNailsReservationsByClientId(id_client));
        map.put("brows", browsDAO.getNailsReservationsByClientId(id_client));
        map.put("nails", nailsDAO.getNailsReservationsByClientId(id_client));
        return map;
    }

    @Override
    public List<Time> getBusyTimesforService(String service, Date date) {
        if (service == null || service.equals("") || date == null)
            return null;

        List<Time> list;
        if (service.equals("Nails") || service.equals("nails")) {
            list = nailsDAO.getBusyTimesforService(date);
        } else if (service.equals("Brows") || service.equals("brows") || service.equals("hair") || service.equals("Hair")) {
            list = browsDAO.getBusyTimesforService(date);
        } else {
            list = makeUpDAO.getBusyTimesforService(date);
        }

        return list;
    }

    @Override
    public void removeReservation(String service, Date date, Time time, long id_client) {
        if (service != null && !service.equals("") && date != null && time != null) {
            if (service.equals("Nails") || service.equals("nails")) {
                System.out.println("Нормуль");
                nailsDAO.removeReservation(date, time, id_client);
            } else if (service.equals("Brows") || service.equals("brows") || service.equals("hair") || service.equals("Hair")) {
                browsDAO.removeReservation(date, time, id_client);
            } else {
                makeUpDAO.removeReservation(date, time, id_client);
            }
        }
    }
}
