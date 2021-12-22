package com.meetowin.meetowin.pages.login;


import com.meetowin.meetowin.pages.forgetPassword.ForgetPasswordService;
import com.meetowin.meetowin.security.config.AppProperties;
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

    @Autowired
    AppProperties appProperties;

    @Autowired
    ForgetPasswordService emailSenderService;


    @PostMapping
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(new AuthResponse(loginService.getToken(loginRequest)));
    }


//    @GetMapping("/user/me")
//    @PreAuthorize("hasRole('USER')")
//    public Users getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
//        return userRepository.findById(userPrincipal.getId())
//                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
//    }





}
