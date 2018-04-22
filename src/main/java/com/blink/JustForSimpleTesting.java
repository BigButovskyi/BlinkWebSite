package com.blink;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class JustForSimpleTesting {
    public static void main(String[] args) throws ParseException {

        Date date = new Date(14,15,22);
        String s = date.toString();
        System.out.println(s);
    }
}