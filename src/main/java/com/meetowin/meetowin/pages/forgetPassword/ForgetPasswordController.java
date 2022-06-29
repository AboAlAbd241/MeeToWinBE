package com.meetowin.meetowin.pages.forgetPassword;

import com.meetowin.meetowin.Dto.Response;
import com.meetowin.meetowin.pages.forgetPassword.DTO.ForgetReq;
import com.meetowin.meetowin.pages.forgetPassword.DTO.ForgetRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/forget-password")
public class ForgetPasswordController {

    @Autowired
    ForgetPasswordService forgetPasswordService;

    @PostMapping
    public Response checkEmail(@RequestBody ForgetReq forgetReq){
        return forgetPasswordService.checkMail(forgetReq.getEmail());
    }

    @PostMapping("/code")
    public ForgetRes checkCode(@RequestBody ForgetReq forgetReq){
        return forgetPasswordService.checkCode(forgetReq);
    }

    @PostMapping("/reset")
    public Response resetPassword(@RequestBody ForgetReq forgetReq){
        return forgetPasswordService.resetPassword(forgetReq);
    }

}
