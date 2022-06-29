package com.meetowin.meetowin.pages.forgetPassword;

import com.meetowin.meetowin.Dto.Response;
import com.meetowin.meetowin.model.ForgetPassword;
import com.meetowin.meetowin.model.Users;
import com.meetowin.meetowin.pages.forgetPassword.DTO.ForgetReq;
import com.meetowin.meetowin.pages.forgetPassword.DTO.ForgetRes;
import com.meetowin.meetowin.repository.ForgetPasswordRepository;
import com.meetowin.meetowin.repository.UserRepository;
import com.meetowin.meetowin.security.exception.BadRequestException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
    @Autowired
    private Configuration config;

//    String toEmail,String body,String subject
    public void sendMail(String email,String code,String name){
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            Map<String, Object> model = new HashMap<>();
            model.put("Code",code);
            model.put("Name",name);

            // set mediaType
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            // add attachment
//            helper.addAttachment("logo.png", new ClassPathResource("logo.png"));

            Template t = config.getTemplate("email-template.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

            helper.setTo(email);
            helper.setText(html, true);
            helper.setSubject("Code verification");
            helper.setFrom("meettowin@gmail.com");
            javaMailSender.send(message);

        } catch (MessagingException | IOException | TemplateException e) {

        }
    }

    public Response checkMail(String email){
        if (!userRepository.existsByEmail(email)){
            throw new BadRequestException("Email address not found.");
        }
        Date date=new Date();
        date.setMinutes(date.getMinutes()+3);
        String code=getCode();
        Optional<Users> users= Optional.of(new Users());
        users=userRepository.findByEmail(email);
        ForgetPassword forgetPassword=new ForgetPassword();
        forgetPassword.setCode(code);
        forgetPassword.setExpiration(date);
        forgetPassword.setUsers(users.get());
        forgetPassword.setEmail(email);
        forgetPasswordRepository.save(forgetPassword);
        sendMail(email,code,users.get().getName());
        Response response=new Response();
        response.setStatus("Done");
        return response;
    }

    public  String getCode(){
        Random random = new Random();
        Integer num = random.ints(10000, 99999).findFirst().getAsInt();
        return num.toString();
    }

    public ForgetRes checkCode(ForgetReq forgetReq){
        ForgetRes response = new ForgetRes();
        List<ForgetPassword> forgetPasswordList = forgetPasswordRepository.findByEmail(forgetReq.getEmail());
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
