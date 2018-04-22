package com.blink.services.blinkServices;

import com.blink.Entities.Nails;

public interface NailsDAOInterface {
    void addService(Nails nails);
   Object[] getNailsReservationsByClientId(long id_client);
}
