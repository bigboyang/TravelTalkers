package com.example.miniproject.connect;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
public class SmtpTEST {

    @Autowired
    private JavaMailSender mailSender;

    @Test
    public void mailSendTest() throws Exception{

        String subject = "test mail subject";
        String content = "mail content";
        String from = "rocknrollloveaffair1999@gmail.com";
        String to = "rocknrollloveaffair1999@gmail.com";

        try{
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper mailHelper = new MimeMessageHelper(mail,"UTF-8");

            mailHelper.setFrom(from);
            mailHelper.setTo(to);
            mailHelper.setSubject(subject);
            mailHelper.setText(content, true);

            mailSender.send(mail);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
