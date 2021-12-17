package com.meetowin.meetowin.pages.signup;

import com.meetowin.meetowin.Dto.Response;
import com.meetowin.meetowin.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("signup")
public class SignUpController {

    @Autowired
    SignUpService service;

    @PostMapping
    public Response addUser(@RequestBody Users users){
            return service.addUser(users);
        }
    }

