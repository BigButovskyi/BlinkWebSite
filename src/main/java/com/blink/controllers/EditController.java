package com.blink.controllers;

import com.blink.Entities.Reservation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/edit/*")
public class EditController {

    @RequestMapping(value = "/edit/getList/{id}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Reservation> getClientReservetionsByID(HttpServletRequest request, HttpServletResponse response) throws IOException {

//        return reservations.getAllReservations();
        return null;
    }
}
