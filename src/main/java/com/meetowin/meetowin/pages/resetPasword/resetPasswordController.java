package com.meetowin.meetowin.pages.resetPasword;


import com.meetowin.meetowin.Dto.Response;
import com.meetowin.meetowin.pages.resetPasword.DTO.ResetReq;
import com.meetowin.meetowin.security.security.CurrentUser;
import com.meetowin.meetowin.security.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reset-password")
public class resetPasswordController {

    @Autowired
    resetPasswordService resetPasswordService;

    @PostMapping
    public Response changePassword(@RequestBody ResetReq ResetReq , @CurrentUser UserPrincipal userPrincipal){
        return resetPasswordService.ChangePassword(ResetReq , userPrincipal);
    }
}
