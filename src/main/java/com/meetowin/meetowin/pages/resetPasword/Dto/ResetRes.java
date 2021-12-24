package com.meetowin.meetowin.pages.resetPasword.Dto;

import lombok.Data;

@Data
public class ResetRes {
    private String status;

    public ResetRes(String status) {
        this.status = status;
    }
    public ResetRes(){
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
