package com.esprit.PI_Sprint3_Mobile.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

//import services.User.UserService;

public class Mailer {
    public static void sendMail(String recepient,int verification_code,String passwordd) throws Exception {
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
        Message message = prepareMessage(session, myAccountEmail, recepient, verification_code,passwordd);

        //Send mail
        Transport.send(message);
    }



    private static Message prepareMessage(Session session, String myAccountEmail, String recepient,int verification_code,String password) {
        try {
//            UserService userService = new UserService();
//            User user = userService.findByEmail(recepient);

            String htmlCode;
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("WELCOME TO ESPRIT GAZINE");
            if(verification_code == 0)
                htmlCode = "<h1> Thank u for joining us !! bellow your login credentials : </h1><br/>" +
                    " <h2><b>Email : "+recepient+"  </b>" +
                    " <b> password : "+password+" </b>" +
                    "</h2>";
            else
                htmlCode = "<h1> Thank u for joining us !!</h1><br/>" +
                    " complete your registration by typing this code : <h2><u> " +verification_code+ "</u></h2>";
            message.setContent(htmlCode, "text/html");
            return message;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
           // Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
