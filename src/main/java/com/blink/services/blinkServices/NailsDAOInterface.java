package com.blink.services.blinkServices;

import com.blink.Entities.Nails;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface NailsDAOInterface {
    void addService(Nails nails);
   Object[] getNailsReservationsByClientId(long id_client);
    List<Time> getBusyTimesforService(Date date);
}
