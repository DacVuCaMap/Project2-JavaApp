package com.example.app.Entity;

import javafx.scene.input.MouseEvent;

import java.util.Properties;
import java.util.Random;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
    public static String sendMail(String recipient){
        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        String myAccountEmail = "amcompany0411@gmail.com";
        String password = "xxskrjjdrajppqzb";
        String otp = buildOTP();
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail,password);
            }

        });
        Message message = prepareMessage(session,myAccountEmail,recipient,otp);
        try {
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return otp;
    }
    private static Message prepareMessage(Session session,String myAccountEmail,String recipient,String otp){
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(recipient));
            message.setSubject("Verification code");
            String htmlContent = "<html><body><p><strong>" + otp + "</strong> is your verification code to activate your account.</p></body></html>";
            message.setContent(htmlContent,"text/html");
            return message;
        } catch (AddressException e) {
            throw new RuntimeException(e);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
    public static String buildOTP(){
        Random random = new Random();
        return String.valueOf(random.nextInt(9))+String.valueOf(random.nextInt(9))+String.valueOf(random.nextInt(9))+String.valueOf(random.nextInt(9));
    }

}
