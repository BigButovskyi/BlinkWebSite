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

    static String emailToRecipient, emailSubject, emailMessage;
    static final String emailFromRecipient = "blinkbeautyspace@gmail.com";

    @Autowired
    private JavaMailSender mailSenderObj;

    // This Method Is Used To Prepare The Email Message And Send It To The Client
    @RequestMapping(value = "/emailVerification", method = RequestMethod.POST)
    public ResponseEntity sendEmailToClient(@RequestBody String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JSONEmailMapper jsonEmailMapper = objectMapper.readValue(json, JSONEmailMapper.class);
        String email = jsonEmailMapper.getEmail();
        //Check if user email is valid
        if (!isValidEmailAddress(email)) {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }

        //Creating verification code
        int code = codeGenerator();
        // Reading Email Form Input Parameters
        emailSubject = "Email Verification";
        emailMessage = "<html> <body><div style=\"font-family: 'Georgia;'\"><h2>Blink Beauty message</h2>" +
                "<span style=\"font-size: 18px;\">Good day!<br>" +
                "Here is the Blink Beauty Guard code you need to put into verification field:</span><br>" +
                "<span><h2>" + code + "</h2></span>" +
                "<span style=\"color:gray\">If it wasn't you, please, ignore this message</span></body></html>";
        emailToRecipient = email;

        mailSenderObj.send(new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {

                MimeMessageHelper mimeMsgHelperObj = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                mimeMsgHelperObj.setTo(emailToRecipient);
                mimeMsgHelperObj.setFrom(emailFromRecipient);
                mimeMsgHelperObj.setText(emailMessage, true);
                mimeMsgHelperObj.setSubject(emailSubject);

            }
        });

        clientService.addEmailAndCode(email, code);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/finalVerification", method = RequestMethod.POST)
    public @ResponseBody boolean finalVerification(@RequestBody String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JSONEmailCodeMapper jsonEmailCodeMapper = objectMapper.readValue(json, JSONEmailCodeMapper.class);
        String email = jsonEmailCodeMapper.getEmail();
        int code = jsonEmailCodeMapper.getCode();
        if (!isValidEmailAddress(email)) {
            return false;
        }
        boolean codeIsValid = clientService.checkCode(email, code);

        return codeIsValid?true:false;
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

    private int codeGenerator(){
        int Min = 1000;
        int Max = 9999;
        return Min + (int)(Math.random() * ((Max - Min) + 1));
    }
}