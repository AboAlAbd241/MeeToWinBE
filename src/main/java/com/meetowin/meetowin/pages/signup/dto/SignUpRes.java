package com.meetowin.meetowin.pages.signup.dto;

import lombok.Data;

@Data
public class SignUpRes {
    private String token;

    public SignUpRes() {
    }

    public SignUpRes(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
