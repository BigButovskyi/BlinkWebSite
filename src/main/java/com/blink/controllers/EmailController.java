package com.blink.controllers;

import com.blink.controllers.jsonMappers.JSONEmailCodeMapper;
import com.blink.controllers.jsonMappers.JSONEmailMapper;
import com.blink.services.clientService.ClientServiceInterface;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@RestController
public class EmailController {
    @Autowired
    private ClientServiceInterface clientService;

    @Autowired
    private EmailSender emailSender;

    // This Method Is Used To Prepare The Email Message And Send It To The Client
    @RequestMapping(value = "/emailVerification", method = RequestMethod.POST)
    public boolean sendEmailToClient(@RequestBody String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JSONEmailMapper jsonEmailMapper = objectMapper.readValue(json, JSONEmailMapper.class);
        String email = jsonEmailMapper.getEmail();
        //Check if user email is valid
        if (!isValidEmailAddress(email)) {
            return false;
        }
        //Creating verification code
        int code = codeGenerator();
        //Sending letter with code to email
        emailSender.sendVerificationCode(email, code);
        //adding code with email to database
        clientService.addEmailAndCode(email, code);
        return true;
    }

    @RequestMapping(value = "/finalVerification", method = RequestMethod.POST)
    public @ResponseBody
    boolean finalVerification(@RequestBody String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JSONEmailCodeMapper jsonEmailCodeMapper = objectMapper.readValue(json, JSONEmailCodeMapper.class);
        String email = jsonEmailCodeMapper.getEmail();
        int code = jsonEmailCodeMapper.getCode();
        if (!isValidEmailAddress(email)) {
            return false;
        }
        boolean codeIsValid = clientService.checkCode(email, code);

        return codeIsValid ? true : false;
    }

    private boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    private int codeGenerator() {
        int Min = 1000;
        int Max = 9999;
        return Min + (int) (Math.random() * ((Max - Min) + 1));
    }
}