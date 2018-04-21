package com.blink.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/edit/*")
public class EditController {

//    @RequestMapping(value = "/edit/getList/{id}", method = RequestMethod.GET, produces = "application/json")
//    public @ResponseBody List<Reservation> getClientReservetionsByID(HttpServletRequest request, HttpServletResponse response) throws IOException {
//
////        return reservations.getAllReservations();
//        return null;
//    }
}
