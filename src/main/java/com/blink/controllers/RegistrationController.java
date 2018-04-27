package com.blink.controllers;

import com.blink.Entities.Client;
import com.blink.controllers.jsonMappers.JSONMapper;
import com.blink.controllers.jsonMappers.JSONServiceDateMapper;
import com.blink.services.blinkServices.BlinkServiceInterface;
import com.blink.services.clientService.ClientServiceInterface;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


@RestController
@RequestMapping(value = "/registration/*")
public class RegistrationController {

    @Autowired
    private ClientServiceInterface clientService;
    @Autowired
    private BlinkServiceInterface blinkService;
    @Autowired
    private EmailSender emailSender;

    @RequestMapping(value = "/registration/getBusyTimesForService", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody TreeSet<Time> getBusyTimesForService(@RequestBody String json) throws IOException, ParseException {
        ObjectMapper objectMapper = new ObjectMapper();
        JSONServiceDateMapper jsonServiceDateMapper = objectMapper.readValue(json, JSONServiceDateMapper.class);
        //Get Service from json
        String service = jsonServiceDateMapper.getService();
        //Get date from json
        Date date = formatDate(jsonServiceDateMapper.getDate(), "yyyy-MM-dd");//format could be yyyy/MM/dd

        long id_client = clientService.getIdClientByEmail(jsonServiceDateMapper.getEmail());

        return blinkService.getBusyTimesforService(id_client,service, date);
    }

    //Adding client and reservation
    @RequestMapping(value = "/registration/addClientAndReservations", method = RequestMethod.POST, produces = "application/json")
    public void addClientAndReservations(@RequestBody String string) throws IOException, ParseException {

        ObjectMapper objectMapper = new ObjectMapper();
        JSONMapper jsonMapper = objectMapper.readValue(string, JSONMapper.class);
        // Getting service
        String service = jsonMapper.getService();
        //Parsing Date
        Date date = formatDate(jsonMapper.getDate(), "yyyy-MM-dd");//format could be yyyy/MM/dd
        //Parsing Time
        Time time = formatTime(jsonMapper.getTime(), "HH:mm");
        //!!!!Registration
        //Adding client to DB
        Client client = jsonMapper.getClient();
        clientService.addClient(client);
        // Sending letter about Client reservation
        emailSender.sendEmailClientReservation(client.getEmail(), service, date, time);
        emailSender.sendAdminAddReservation(client.getEmail(), client.getName(), client.getPhone(), service, date, time);
        //Adding particular service
        long id_client = clientService.getIdClientByEmail(client.getEmail());
        blinkService.addService(service, date, time, id_client);

    }

    private Date formatDate(String dateString, String format) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = new Date(simpleDateFormat.parse(dateString).getTime());
        return date;
    }

    private Time formatTime(String time, String format) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        long ms = simpleDateFormat.parse(time).getTime();
        return new Time(ms);
    }

}