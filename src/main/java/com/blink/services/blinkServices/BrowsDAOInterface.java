package com.blink.services.blinkServices;

import com.blink.Entities.Brows;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface BrowsDAOInterface {

    void addService(Brows brows);
    Object[] getNailsReservationsByClientId(long id_client);
    List<Time> getBusyTimesforService(Date date);
}
