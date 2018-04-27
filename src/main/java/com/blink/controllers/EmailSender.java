package com.blink.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import org.springframework.stereotype.Controller;

import javax.mail.internet.MimeMessage;

import java.sql.Date;
import java.sql.Time;

@Controller
public class EmailSender {

    private String emailToRecipient, emailSubject, emailMessage;
    private final String emailFromRecipient = "blinkbeautyspace@gmail.com";

    @Autowired
    private JavaMailSender mailSenderObj;

    public void sendVerificationCode(String email, int code) {
        String subject = "Email Verification";
        String message = "<html><body>" +
                "<div style=\"font-family: 'Georgia';\"><h2>Blink Beauty message</h2>" +
                "<span style=\"font-size: 18px;\">Good day!<br>" +
                "Here is the Blink Beauty Guard code you need to put into verification field:</span><br>" +
                "<span><h2>" + code + "</h2></span>" +
                "<span style=\"color:gray\">If it wasn't you, please, ignore this message</span>" +
                "</body></html>";
        send(email, subject, message);
    }

    public void sendEmailClientReservation(String email, String service, Date date, Time time) {
        String subject = "Service Reservation";
        String message = "<html><body>" +
                "<h2>Hello!</h2>" +
                "<h3><div>You have just reserved service '" + service + "' </div>" +
                "<div>Date: " + date.toString() + " </div>" +
                "<div>Time: " + time.toString() + "</div>" +
                "<div>Yours Blink Beauty Space</div>" +
                "</h3>" +
                "</body></html>";
        send(email, subject, message);
    }

    public void sendAdminAddReservation(String email, String name, String phone,
                                        String service, Date date, Time time) {
        String subject = "Client Reservation";
        String message = "<html><body>" +
                "<h2>Hello!</h2>" +
                "<h3><div>Client:</div>" +
                "<div>Name: " + name + "</div>" +
                "<div>email: " + email + "</div>" +
                "<div>Phone: " + phone + "</div><br>" +
                "<div>Has reserved:</div>" +
                "<div>Service: " + service + "</div>" +
                "<div>Date: " + date.toString() + " </div>" +
                "<div>Time: " + time.toString() + "</div>" +
                "<div>Successfully</div><br>" +
                "<div>Yours Little Robo-MailSender</div>" +
                "</h3>" +
                "</body></html>";
        send(emailFromRecipient, subject, message);
    }

    public void sendEmailWithChangeParameters(String email, String service,
                                              Date old_date, Time old_time, Date new_date, Time new_time) {
        String subject = "Reservation Update";
        String message = "<html><body>" +
                "<h2>Hello!</h2>" +
                "<h3><div>You have just edited your booking '" + service + "' </div>" +
                "<div>From</div>" +
                "<div>Date: " + old_date.toString() + " </div>" +
                "<div>Time: " + old_time.toString() + "</div>" +
                "<div>To</div>" +
                "<div>Date: " + new_date.toString() + " </div>" +
                "<div>Time: " + new_time.toString() + "</div>" +
                "<div>Yours Blink Beauty Space</div>" +
                "</h3>" +
                "</body></html>";
        send(email, subject, message);
    }

    public void sendAdminAboutChanges(String email, String client_name, String phone, String service, Date old_date, Time old_time,
                                      Date new_date, Time new_time) {
        String subject = "Client Changes";
        String message = "<html><body>" +
                "<h2>Hello!</h2>" +
                "<h3><div>Client:</div>" +
                "<div>Name: " + client_name + "</div>" +
                "<div>email: " + email + "</div>" +
                "<div>Phone: " + phone + "</div><br>" +
                "<div>Has changed '" + service + "'</div>" +
                "<div>From</div>" +
                "<div>Old Date: " + old_date.toString() + " </div>" +
                "<div>Old Time: " + old_time.toString() + "</div>" +
                "<div>To</div>" +
                "<div>New Date: " + new_date.toString() + " </div>" +
                "<div>New Time: " + new_time.toString() + "</div><br>" +
                "<div>Yours Little Robo-MailSender</div>" +
                "</h3>" +
                "</body></html>";
        send(emailFromRecipient, subject, message);
    }

    public void sendAdminAboutDeleteReservation(String email, String name, String phone,
                                        String service, Date date, Time time) {
        String subject = "Client Reservation";
        String message = "<html><body>" +
                "<h2>Hello!</h2>" +
                "<h3><div>Client:</div>" +
                "<div>Name: " + name + "</div>" +
                "<div>email: " + email + "</div>" +
                "<div>Phone: " + phone + "</div><br>" +
                "<div>Has canceled his reservation:</div>" +
                "<div>Service: " + service + "</div>" +
                "<div>Date: " + date.toString() + " </div>" +
                "<div>Time: " + time.toString() + "</div>" +
                "<div>Successfully</div><br>" +
                "<div>Yours Little Robo-MailSender</div>" +
                "</h3>" +
                "</body></html>";
        send(emailFromRecipient, subject, message);
    }


    public void sendEmailAboutDeleteReservation(String email, String service, Date date, Time time) {
        String subject = "Reservation Remove";
        String message = "<html><body>" +
                "<h2>Hello!</h2>" +
                "<h3><div>You have just deleted your booking on service '" + service + "' </div>" +
                "<div>Date: " + date.toString() + " </div>" +
                "<div>Time: " + time.toString() + "</div>" +
                "<div>Yours Blink Beauty Space</div>" +
                "</h3>" +
                "</body></html>";
        send(email, subject, message);
    }

    private void send(String toEmail, String subject, String message) {
        emailSubject = subject;
        emailMessage = message;
        emailToRecipient = toEmail;

        mailSenderObj.send(new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {

                MimeMessageHelper mimeMsgHelperObj = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                mimeMsgHelperObj.setTo(emailToRecipient);
                mimeMsgHelperObj.setFrom(emailFromRecipient);
                mimeMsgHelperObj.setText(emailMessage, true);
                mimeMsgHelperObj.setSubject(emailSubject);

            }
        });
    }


}
