package com.blink.controllers;

import com.blink.controllers.jsonMappers.JSONEmailMapper;
import com.blink.services.blinkServices.BlinkServiceInterface;
import com.blink.services.clientService.ClientServiceInterface;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @RequestMapping(value = "/edit/updateClientReservation", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody void updateClientReservation(@RequestBody String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

    }

}
