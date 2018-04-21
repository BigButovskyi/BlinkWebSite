package com.blink.services.blinkServices;

import java.sql.Date;
import java.sql.Time;

public interface BlinkServiceInterface {
    void addService(String service, Date date, Time time, long id_client);
}
