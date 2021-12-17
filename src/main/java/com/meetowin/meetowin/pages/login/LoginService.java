package com.meetowin.meetowin.pages.login;

import com.meetowin.meetowin.model.Users;
import com.meetowin.meetowin.pages.login.Dto.LoginReq;
import com.meetowin.meetowin.pages.login.Dto.LoginRes;
import com.meetowin.meetowin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

//logic is here
@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    public LoginRes checkAccount(LoginReq loginReq){
        LoginRes loginRes=new LoginRes();
        if (!loginReq.getPassword().isEmpty()&&!loginReq.getUsername().isEmpty()){
            Optional<Users> user=userRepository.findByUsername(loginReq.getUsername());
            if (user.isPresent()&&user.get().getPassword().equals(loginReq.getPassword())){
                loginRes.setId(user.get().getId());
                loginRes.setStatus("Found");
                return loginRes;
            }else {
                loginRes.setStatus("Not Found");
                return loginRes;
            }
        }
        else{
            loginRes.setStatus("Not Found");
            return loginRes;
        }

    }
}
