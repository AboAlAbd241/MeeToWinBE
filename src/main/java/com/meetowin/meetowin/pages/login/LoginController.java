package com.meetowin.meetowin.pages.login;

import com.meetowin.meetowin.pages.login.Dto.LoginReq;
import com.meetowin.meetowin.pages.login.Dto.LoginRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//for routing
@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public LoginRes login(@RequestBody LoginReq loginReq){
        return loginService.checkAccount(loginReq);
    }


}
