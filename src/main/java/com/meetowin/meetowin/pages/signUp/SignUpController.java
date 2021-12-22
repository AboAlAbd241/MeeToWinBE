package com.meetowin.meetowin.pages.signUp;

import com.meetowin.meetowin.security.payload.ApiResponse;
import com.meetowin.meetowin.security.payload.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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



