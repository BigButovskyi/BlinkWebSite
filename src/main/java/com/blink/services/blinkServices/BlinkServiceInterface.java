package com.blink.services.blinkServices;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Map;

public interface BlinkServiceInterface {
    void addService(String service, Date date, Time time, long id_client);
    List<Time> getBusyTimesforService(String service, Date date);
    Map<String, Object[]> getClientReservations(long id_client);
    void removeReservation(String service, Date date, Time time, long id_client);

}
