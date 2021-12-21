package com.meetowin.meetowin.pages.login;


import com.meetowin.meetowin.repository.UserRepository;
import com.meetowin.meetowin.security.payload.LoginRequest;
import com.meetowin.meetowin.security.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

//logic is here
@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenProvider tokenProvider;

//    public LoginRes checkAccount(LoginReq loginReq){
//        LoginRes loginRes=new LoginRes();
//        if (!loginReq.getPassword().isEmpty()&&!loginReq.getUsername().isEmpty()){
//            Optional<Users> user=userRepository.findByUsername(loginReq.getUsername());
//            if (user.isPresent()&&user.get().getPassword().equals(loginReq.getPassword())){
//                loginRes.setId(user.get().getId());
//                loginRes.setStatus("Found");
//                return loginRes;
//            }else {
//                loginRes.setStatus("Not Found");
//                return loginRes;
//            }
//        }
//        else{
//            loginRes.setStatus("Not Found");
//            return loginRes;
//        }
//
//    }
//
    public String getToken(LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        //here is the token
        return  tokenProvider.createToken(authentication);
    }
}
