package com.meetowin.meetowin.pages.resetPasword;


import com.meetowin.meetowin.Dto.Response;
import com.meetowin.meetowin.model.Users;
import com.meetowin.meetowin.pages.resetPasword.DTO.ResetReq;
import com.meetowin.meetowin.repository.UserRepository;
import com.meetowin.meetowin.security.exception.ResourceNotFoundException;
import com.meetowin.meetowin.security.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static java.lang.String.valueOf;

@Service
public class resetPasswordService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Users CurrentUser(UserPrincipal userPrincipal){
        return  userRepository.findById(userPrincipal.getId()).orElseThrow(()
                -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));

    }

    public Response ChangePassword(ResetReq ResetReq , UserPrincipal userPrincipal) {
        Response response = new Response();
        Users user = CurrentUser(userPrincipal);
        PasswordEncoder passencoder = new BCryptPasswordEncoder();
        String encodedPassword = user.getPassword();
        if (passencoder.matches(ResetReq.getOldPassword(), encodedPassword)) {
            userRepository.existsByEmail(user.getEmail());
            user.setPassword(passwordEncoder.encode(ResetReq.getNewPassword()));
            response.setStatus("your password change successfully");
           userRepository.save(user);
            return response;
        }
        response.setStatus("your old password is wrong please triaging");
        return response;

    }

}
