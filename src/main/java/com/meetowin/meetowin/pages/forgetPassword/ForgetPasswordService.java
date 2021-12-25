package com.meetowin.meetowin.pages.forgetPassword;

import com.meetowin.meetowin.Dto.Response;
import com.meetowin.meetowin.model.ForgetPassword;
import com.meetowin.meetowin.model.Users;
import com.meetowin.meetowin.pages.forgetPassword.Dto.ForgetReq;
import com.meetowin.meetowin.pages.forgetPassword.Dto.ForgetRes;
import com.meetowin.meetowin.repository.ForgetPasswordRepository;
import com.meetowin.meetowin.repository.UserRepository;
import com.meetowin.meetowin.security.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ForgetPasswordService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ForgetPasswordRepository forgetPasswordRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

//    String toEmail,String body,String subject
    public void sendMail(String email,String code){
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setFrom("meettowin@gmail.com");
        simpleMailMessage.setTo(email);
        simpleMailMessage.setText(code);
        simpleMailMessage.setSubject("Code verification");

        try {
            javaMailSender.send(simpleMailMessage);
        }catch (Exception e){
            System.out.println(e);
        }

    }

    public Response checkMail(String email){
        if (!userRepository.existsByEmail(email)){
            throw new BadRequestException("Email address not found.");
        }
        Date date=new Date();
        date.setMinutes(date.getMinutes()+3);
        String code=getCode();
        sendMail(email,code);
        Optional<Users> users= Optional.of(new Users());
        users=userRepository.findByEmail(email);
        ForgetPassword forgetPassword=new ForgetPassword();
        forgetPassword.setCode(code);
        forgetPassword.setExpiration(date);
        forgetPassword.setUsers(users.get());
        forgetPassword.setEmail(email);
        forgetPasswordRepository.save(forgetPassword);
        Response response=new Response();
        response.setStatus("Done");
        return response;
    }

    public  String getCode(){
        Random random = new Random();
        Integer num =  random.ints(10000, 99999).findFirst().getAsInt();
        return num.toString();
    }

    public ForgetRes checkCode(ForgetReq forgetReq){
        ForgetRes response=new ForgetRes();
        List<ForgetPassword> forgetPasswordList=forgetPasswordRepository.findByEmail(forgetReq.getEmail());
        for (ForgetPassword forgetPassword:forgetPasswordList){
            if (forgetPassword.getCode().equals(forgetReq.getCode())){
                if (forgetPassword.isValid()&&forgetPassword.getExpiration().after(new Date())){
                    forgetPassword.setToken(String.valueOf(getCode().hashCode()*114));
                    forgetPasswordRepository.save(forgetPassword);
                    response.setStatus("Valid code");
                    response.setToken(forgetPassword.getToken());
                    response.setId(forgetPassword.getId());
                    return response;

                }
            }
        }
        response.setStatus("invalid code");
        return response;
    }

    public Response resetPassword(ForgetReq forgetReq){
        Response response=new Response();
        Optional<ForgetPassword> forgetPassword=forgetPasswordRepository.findById(forgetReq.getId());
        if (forgetReq.getToken().equals(forgetPassword.get().getToken())&&!forgetReq.getToken().equals("null")){
            Optional<Users> users=userRepository.findByEmail(forgetReq.getEmail());
            users.get().setPassword(passwordEncoder.encode(forgetReq.getPassword()));
            forgetPassword.get().setValid(false);
            forgetPassword.get().setCode("null");
            forgetPassword.get().setToken("null");
            forgetPasswordRepository.save(forgetPassword.get());
            userRepository.save(users.get());
            response.setStatus("Password Reset successfully ");
            return response;
        }
        response.setStatus("Password Reset fail");
        return response;
    }


}
