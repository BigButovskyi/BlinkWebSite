package com.blink;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class JustForSimpleTesting {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        long ms = simpleDateFormat.parse("24:00").getTime();
        System.out.println(new Time(ms));
    }
}