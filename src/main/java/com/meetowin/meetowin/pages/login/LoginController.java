package com.meetowin.meetowin.pages.login;

import org.springframework.web.bind.annotation.*;

//for routing
@RestController
@RequestMapping("login")
public class LoginController {


    @PostMapping
    public String login(@RequestBody String info){
        return info;
    }


}
