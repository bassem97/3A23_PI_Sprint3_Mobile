package com.esprit.PI_Sprint3_Mobile.utils;

import com.esprit.PI_Sprint3_Mobile.entities.Event;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Properties;

public class EventParticipateMail {

    public static void sendMail(String recepient, Event event) {

        Properties properties = new Properties();

        //Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");

        //Your gmail address
        String myAccountEmail = "espritgazine@gmail.com";
        //Your gmail password
        String password = "lrpaqmelmjaxouoh";

        //Create a session with account credentials
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        //Prepare email message
        Message message = prepareMessage(session, myAccountEmail, recepient, event);

        //Send mail
        try {
            Transport.send(message);
            System.out.println("Message sent successfully");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient, Event event) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Participation à Un Event");
            String pattern = "dd/MM/yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String htmlCode = "<h1 style='color:red'> Evènement: " + event.getName() + " </h1> <br/> " +
                    "<h2><b> L'évènement aura lieu le " + event.getDate().toLocalDate() + " </b></h2>" +
                    "<br/><p style='color:blue; text-decoration: underline'>Soyez Nombreux #EspritGazine</p>";
            message.setContent(htmlCode, "text/html");
            return message;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
