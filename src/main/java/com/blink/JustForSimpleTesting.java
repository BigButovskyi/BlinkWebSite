package com.blink;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class JustForSimpleTesting {
    public static void main(String[] args) {
        java.util.Date utilDate = new java.util.Date();
        Date sqlDate = new java.sql.Date(utilDate.getTime());
        System.out.println(sqlDate);
    }
}