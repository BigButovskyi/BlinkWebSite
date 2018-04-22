package com.blink.services.blinkServices;

import com.blink.Entities.Brows;

public interface BrowsDAOInterface {

    void addService(Brows brows);
    Object[] getNailsReservationsByClientId(long id_client);
}
