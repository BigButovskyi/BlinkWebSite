package com.blink.controllers;

import com.blink.controllers.jsonMappers.JSONEmailMapper;
import com.blink.controllers.jsonMappers.RemoveMapper;
import com.blink.services.blinkServices.BlinkServiceInterface;
import com.blink.services.clientService.ClientServiceInterface;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.rmi.Remote;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

@RestController
@RequestMapping(value = "/delete/*")
public class DeleteController {

    @Autowired
    private ClientServiceInterface clientService;
    @Autowired
    private BlinkServiceInterface blinkService;

    @RequestMapping(value = "/delete/getClientReservations", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody Map<String, Object[]> getClientServices(@RequestBody String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JSONEmailMapper jsonEmailMapper = objectMapper.readValue(json, JSONEmailMapper.class);
        String email = jsonEmailMapper.getEmail();
        //get client id
//        long id_client = clientService.getIdClientByPhone(phone);
        long id_client = clientService.getIdClientByEmail(email);
        //Sending client's id
        return (id_client != 0)?
                blinkService.getClientReservations(id_client)
                :null;
    }

    @RequestMapping(value = "/delete/removeReservation", method = RequestMethod.POST, produces = "application/json")
    public void deleteClientReservation(@RequestBody String json) throws IOException, ParseException {
        ObjectMapper objectMapper = new ObjectMapper();
        RemoveMapper removeMapper = objectMapper.readValue(json, RemoveMapper.class);
        String email = removeMapper.getEmail();
        String service = removeMapper.getService();
        ////Parsing Date
        Date date = formatDate(removeMapper.getDate(), "yyyy-MM-dd");//format could be yyyy/MM/dd
        //Parsing Time
        Time time = formatTime(removeMapper.getTime(), "HH:mm");

        //get client id
        long id_client = clientService.getIdClientByEmail(email);
        //remove service by client id and by service, date, time
        blinkService.removeReservation(service, date, time, id_client);

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