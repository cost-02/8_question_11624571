package com.example;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {

    public void sendEmail(String from, String password, String to, String subject, String text) {
        // Impostazioni SMTP
        String host = "smtp.example.com"; // Sostituire con il tuo host SMTP

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587"); // Porta SMTP standard con TLS

        // Ottiene la sessione
        Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new javax.mail.PasswordAuthentication(from, password);
                }
            });

        try {
            // Crea un messaggio email
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(text);

            // Invia il messaggio
            Transport.send(message);
            System.out.println("Mail inviata correttamente!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
