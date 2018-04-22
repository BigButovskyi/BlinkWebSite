package com.blink.services.blinkServices;

import com.blink.Entities.MakeUp;

public interface MakeUpDAOInterface {
     void addService(MakeUp makeUp);
     Object[] getNailsReservationsByClientId(long id_client);
}
