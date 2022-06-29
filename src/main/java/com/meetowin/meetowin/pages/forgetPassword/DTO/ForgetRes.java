package com.meetowin.meetowin.pages.forgetPassword.DTO;

import lombok.Data;

@Data
public class ForgetRes {
    private String token;
    private String status;
    private Long id;
    public ForgetRes() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
