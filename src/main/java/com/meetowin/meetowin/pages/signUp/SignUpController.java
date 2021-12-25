package com.meetowin.meetowin.pages.signUp;

import com.meetowin.meetowin.Dto.Response;
import com.meetowin.meetowin.repository.UserRepository;
import com.meetowin.meetowin.security.payload.ApiResponse;
import com.meetowin.meetowin.security.payload.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("signup")
public class SignUpController {

    @Autowired
    private SignUpService service;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JavaMailSender mailSender;



/*@PostMapping()
public  ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest ) {
    return ResponseEntity.created(service.testUser(signUpRequest))
            .body(new ApiResponse(true, "User registered successfully@"));
}*/

@PostMapping()
    public Response registerUser(@RequestBody SignUpRequest signUpRequest){
    return service.addUser(signUpRequest);
}

@GetMapping("/confirm-account")
    public Response ConfirmAccount( @RequestParam("token")String confirmationToken){
    return service.ConfirmAccount(confirmationToken);
}



}



