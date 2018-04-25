package com.blink.controllers;

import com.blink.controllers.jsonMappers.JSONEmailMapper;
import com.blink.controllers.jsonMappers.JSONUpdateMapper;
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
import java.util.Map;

@RestController
@RequestMapping(value = "/edit/*")
public class EditController {

    @Autowired
    private ClientServiceInterface clientService;
    @Autowired
    private BlinkServiceInterface blinkService;

    @RequestMapping(value = "/edit/getClientReservations", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody Map<String, Object[]> getClientServices(@RequestBody String json) throws IOException {
        ObjectMapper objectMapper;
        objectMapper = new ObjectMapper();
        JSONEmailMapper jsonEmailMapper = objectMapper.readValue(json, JSONEmailMapper.class);
        String email = jsonEmailMapper.getEmail();
        //get client id
//        long id_client = clientService.getIdClientByPhone(phone);
        long id_client = clientService.getIdClientByEmail(email);
        // Sending client's id
        return (id_client != 0)?
                blinkService.getClientReservations(id_client)
                :null;
    }

    @RequestMapping(value = "/edit/updateClientReservation", method = RequestMethod.PUT, produces = "application/json")
    public void updateClientReservation(@RequestBody String json) throws IOException, ParseException {
        ObjectMapper objectMapper = new ObjectMapper();
        JSONUpdateMapper jsonUpdateMapper = objectMapper.readValue(json, JSONUpdateMapper.class);
        String email = jsonUpdateMapper.getEmail();
        String service = jsonUpdateMapper.getService();

        Date old_date = formatDate(jsonUpdateMapper.getOld_date(), "yyyy-MM-dd");
        Time old_time = formatTime(jsonUpdateMapper.getOld_time(),"HH:mm");

        Date new_date = formatDate(jsonUpdateMapper.getNew_date(), "yyyy-MM-dd");
        Time new_time = formatTime(jsonUpdateMapper.getNew_time(), "HH:mm");

        long id_client = clientService.getIdClientByEmail(email);

        blinkService.updateService(id_client, service,old_date,old_time,new_date,new_time);
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
