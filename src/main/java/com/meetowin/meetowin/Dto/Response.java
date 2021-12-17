package com.meetowin.meetowin.Dto;

import lombok.Data;

@Data
public class Response {
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status;

}
