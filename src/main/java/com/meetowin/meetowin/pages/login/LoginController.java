package com.meetowin.meetowin.pages.login;


import com.meetowin.meetowin.security.payload.AuthResponse;
import com.meetowin.meetowin.security.payload.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//for routing
@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private LoginService loginService;

//    @PostMapping
//    public LoginRes login(@RequestBody LoginReq loginReq){
//        return loginService.checkAccount(loginReq);
//    }

    @PostMapping
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(new AuthResponse(loginService.getToken(loginRequest)));
    }





}
