package com.blink.services.blinkServices;

import com.blink.Entities.MakeUp;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface MakeUpDAOInterface {
     void addService(MakeUp makeUp);
     Object[] getNailsReservationsByClientId(long id_client);
     List<Time> getBusyTimesforService(Date date);
}
