package com.meetowin.meetowin.pages.signUp;

import com.meetowin.meetowin.model.AuthProvider;
import com.meetowin.meetowin.model.Users;
import com.meetowin.meetowin.pages.login.LoginService;
import com.meetowin.meetowin.repository.UserRepository;
import com.meetowin.meetowin.security.exception.BadRequestException;
import com.meetowin.meetowin.security.payload.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Service
public class SignUpService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private LoginService loginService;

//    public Response addUser(Users users) {
//        Response res = new Response();
//        if (users.getUsername()==null||users.getPassword()==null||users.getEmail()==null||users.getPhoneNumber()==null||users.getName()==null||users.getUsername().isEmpty()||users.getPassword().isEmpty()||users.getEmail().isEmpty()||users.getPhoneNumber().isEmpty()||users.getName().isEmpty()){
//            res.setStatus("Missing Some Information");
//            return res;
//        }else
//        if (userRepository.findByUsername(users.getUsername()).isPresent()) {
//            res.setStatus("Username Taken");
//            return res;
//        }else if (userRepository.findByEmail(users.getEmail()).isPresent()){
//            res.setStatus("Email Taken");
//            return res;
//        }else {
//            userRepository.save(users);
//            res.setStatus("Done");
//            return res;
//        }
//    }
//
    public URI addUser(SignUpRequest signUpRequest){
        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
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

    }
}
