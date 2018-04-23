package com.blink.services.blinkServices;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface NailsDAOInterface {
    void addService(Date date, Time time, long id_client);
   Object[] getNailsReservationsByClientId(long id_client);
    List<Time> getBusyTimesforService(Date date);
    void removeReservation(Date date, Time time, long id_client);
}
