package com.meetowin.meetowin.pages.signUp;

import com.meetowin.meetowin.Dto.Response;
import com.meetowin.meetowin.model.AuthProvider;
import com.meetowin.meetowin.model.VerifiedEmail;
import com.meetowin.meetowin.model.Users;
import com.meetowin.meetowin.repository.ConfirmationTokenRepository;
import com.meetowin.meetowin.repository.UserRepository;
import com.meetowin.meetowin.security.exception.BadRequestException;
import com.meetowin.meetowin.security.payload.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SignUpService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;


    @Autowired
    private JavaMailSender javaMailSender;


    public void sendMail(String email, String Subject, String Text) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("meettowin@gmail.com");
        simpleMailMessage.setTo(email);
        simpleMailMessage.setText(Text);
        simpleMailMessage.setSubject(Subject);

        try {
            javaMailSender.send(simpleMailMessage);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    /*public URI addUser(SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Email address already in use.");
        }

        // Creating user's account
        Users user = new Users();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setProvider(AuthProvider.local);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Users result = userRepository.save(user);



//        //todo edit the path
        return ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/me")
                .buildAndExpand(result.getId()).toUri();

    }*/


    public Response addUser(SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Email address already in use.");
        }


        // Creating user's account
        Users user = new Users();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setProvider(AuthProvider.local);
        user.setUsername(signUpRequest.getUsername());


        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);

        VerifiedEmail confirmationToken = new VerifiedEmail(user);

        confirmationTokenRepository.save(confirmationToken);

        sendMail(user.getEmail(), "Complete Registration!", "To confirm your account, please click here : "
                + "http://localhost:8080/signup/confirm-account?token=" + confirmationToken.getConfirmationToken());
        Response response = new Response();
        response.setStatus("done");
        return response;
    }

    public Response ConfirmAccount(String confirmationToken){
        VerifiedEmail token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        Response response = new Response();

        if(token != null)
        {
            Optional<Users> user = userRepository.findById(token.getUserEntity().getId());
            user.get().setEmailVerified(true);
            userRepository.save(user.get());
            response.setStatus("accountVerified");
        }
        else
        {
            response.setStatus("The link is invalid or broken!");
        }
        return response;
    }
}
