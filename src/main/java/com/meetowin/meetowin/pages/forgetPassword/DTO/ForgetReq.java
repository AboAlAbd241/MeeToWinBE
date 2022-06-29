package com.meetowin.meetowin.pages.forgetPassword.DTO;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class ForgetReq {
    @Email
    private String email;
    private String code;
    private String password;
    private String token;
    private Long id;

    public ForgetReq() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
