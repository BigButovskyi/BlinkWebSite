package com.blink.services.blinkServices;

import com.blink.Entities.Brows;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface BrowsDAOInterface {
    void addService(Date date, Time time, long id_client);
    Object[] getNailsReservationsByClientId(long id_client);
    List<Time> getBusyTimesforService(Date date);
    void removeReservation(Date date, Time time, long id_client);
    void updateService(long id_client, Date old_date, Time old_time, Date new_date, Time new_time);
}
