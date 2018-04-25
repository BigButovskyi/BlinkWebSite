package com.blink.services.blinkServices;

import java.sql.Date;
import java.sql.Time;
import java.util.Map;
import java.util.TreeSet;

public interface BlinkServiceInterface {
    void addService(String service, Date date, Time time, long id_client);
    TreeSet<Time> getBusyTimesforService(long id_client, String service, Date date);
    Map<String, Object[]> getClientReservations(long id_client);
    void removeReservation(String service, Date date, Time time, long id_client);
    void updateService(long id_client,String service,Date old_date, Time old_time, Date new_date,Time new_time);

}
