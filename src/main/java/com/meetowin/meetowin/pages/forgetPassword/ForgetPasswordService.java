package com.meetowin.meetowin.pages.forgetPassword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ForgetPasswordService {
    @Autowired
    private JavaMailSender javaMailSender;

//    String toEmail,String body,String subject
    public void sendMail(){
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setFrom("meettowin@gmail.com");
        simpleMailMessage.setTo("abodhourani@gmail.com");
        simpleMailMessage.setText("Hello my friend ");
        simpleMailMessage.setSubject("I love you");

        try {
            javaMailSender.send(simpleMailMessage);
        }catch (Exception e){
            System.out.println(e);
        }

    }
}
