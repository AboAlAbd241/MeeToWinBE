package com.meetowin.meetowin.pages.signup;

import com.meetowin.meetowin.Dto.Response;
import com.meetowin.meetowin.model.Users;
import com.meetowin.meetowin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {

    @Autowired
    private UserRepository userRepository;

    public Response addUser(Users users) {
        Response res = new Response();
        if (users.getUsername()==null||users.getPassword()==null||users.getEmail()==null||users.getPhoneNumber()==null||users.getName()==null||users.getUsername().isEmpty()||users.getPassword().isEmpty()||users.getEmail().isEmpty()||users.getPhoneNumber().isEmpty()||users.getName().isEmpty()){
            res.setStatus("Missing Some Information");
            return res;
        }else
        if (userRepository.findByUsername(users.getUsername()).isPresent()) {
            res.setStatus("Username Taken");
            return res;
        }else if (userRepository.findByEmail(users.getEmail()).isPresent()){
            res.setStatus("Email Taken");
            return res;
        }else {
            userRepository.save(users);
            res.setStatus("Done");
            return res;
        }
    }
}
