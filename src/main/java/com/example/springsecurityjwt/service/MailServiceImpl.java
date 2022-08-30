package com.example.springsecurityjwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class MailServiceImpl implements MailService{
    private static final String CONTENT_TYPE_TEXT_HTML = "text/html;charset=\"utf-8\"";

    @Value("${config.mail.host}")
    private String host;
    @Value("${config.mail.port}")
    private String port;
    @Value("${config.mail.username}")
    private String email;
    @Value("${config.mail.password}")
    private String password;

    @Autowired
    ThymeleafService thymeleafService;

    @Override
    public void sendMail(String mail) {
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", port);

        Session session = Session.getInstance(props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(email, password);
                    }
                });
        Message message = new MimeMessage(session);
        String[] a = mail.split(";");
        for (String s : a) {
            try {
                message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(s)});
                message.setFrom(new InternetAddress(email));
                message.setSubject("Sgintk Test____  ");
                message.setContent(thymeleafService.getContent(), CONTENT_TYPE_TEXT_HTML);
                Transport.send(message);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }

    }
}