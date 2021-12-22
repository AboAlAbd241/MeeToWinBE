package com.meetowin.meetowin.pages.login;


import com.meetowin.meetowin.model.Users;
import com.meetowin.meetowin.pages.forgetPassword.ForgetPasswordService;
import com.meetowin.meetowin.repository.UserRepository;
import com.meetowin.meetowin.security.config.AppProperties;
import com.meetowin.meetowin.security.exception.ResourceNotFoundException;
import com.meetowin.meetowin.security.payload.AuthResponse;
import com.meetowin.meetowin.security.payload.LoginRequest;
import com.meetowin.meetowin.security.security.CurrentUser;
import com.meetowin.meetowin.security.security.UserPrincipal;
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
    private AppProperties appProperties;

    @Autowired
    private ForgetPasswordService emailSenderService;

    @Autowired
    private UserRepository userRepository;


    @PostMapping
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(new AuthResponse(loginService.getToken(loginRequest)));
    }

//    @GetMapping("/getUsers")
//    public Users getUsers(@Valid @RequestBody LoginRequest loginRequest) {
//        return ResponseEntity.ok(new AuthResponse(loginService.getToken(loginRequest)));
//    }


    @GetMapping("/user/me")
//    @PreAuthorize("hasRole('USER')")
    public Users getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }






}
