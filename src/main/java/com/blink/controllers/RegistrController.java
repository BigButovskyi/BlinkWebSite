package com.blink.controllers;

import com.blink.Entities.Client;
import com.blink.services.ClientServiceInterface;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


@RestController
@RequestMapping(value = "/registration/*")
public class RegistrController {

    @Autowired
    private ClientServiceInterface clientService;

    @RequestMapping(value = "/registration/{service}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Time> getBusyTimesforService
            (@PathVariable("service") String service, @RequestBody Date date) throws IOException {

//        return clientService.getBusyTimesforService(service, date);
        return null;
    }


    //Доделать json объект принимать должен
    @RequestMapping(value = "/registration/addClientAndReservations", method = RequestMethod.POST, produces = "application/json")
    public void addClientAndReservations(@RequestBody String string) throws IOException, ParseException {

        ObjectMapper objectMapper = new ObjectMapper();
        JSONMapper jsonMapper = objectMapper.readValue(string, JSONMapper.class);
        //Parsing Date
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        Date date = new Date(simpleDateFormat.parse(jsonMapper.getDate()).getTime());
        //Parsing Time
        simpleDateFormat = new SimpleDateFormat("hh:mm");
        long ms = simpleDateFormat.parse(jsonMapper.getTime()).getTime();
        Time time = new Time(ms);
        //Registration
        Client client = jsonMapper.getClient();
        clientService.addClient(client);

        String service = jsonMapper.getService();

    }

}