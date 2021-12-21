package com.meetowin.meetowin.pages.signup;

import com.meetowin.meetowin.Dto.Response;
import com.meetowin.meetowin.model.AuthProvider;
import com.meetowin.meetowin.model.Users;
import com.meetowin.meetowin.pages.login.LoginService;
import com.meetowin.meetowin.pages.signup.dto.SignUpRes;
import com.meetowin.meetowin.repository.UserRepository;
import com.meetowin.meetowin.security.exception.BadRequestException;
import com.meetowin.meetowin.security.payload.ApiResponse;
import com.meetowin.meetowin.security.payload.AuthResponse;
import com.meetowin.meetowin.security.payload.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("signup")
public class SignUpController {

    @Autowired
    private SignUpService service;



//    @PostMapping
//    public Response addUser(@RequestBody Users users){
//            return service.addUser(users);
//        }


@PostMapping
public  ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
    return ResponseEntity.created(service.addUser(signUpRequest))
            .body(new ApiResponse(true, "User registered successfully@"));
}
    }



