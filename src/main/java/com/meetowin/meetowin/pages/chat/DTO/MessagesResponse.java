package com.meetowin.meetowin.pages.chat.DTO;

import com.meetowin.meetowin.common.EncryptIds;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessagesResponse {
    private String sender_id;
    private String receiver_id;
    private String message;
    private Date sent_date;

    public MessagesResponse() {
    }

    public MessagesResponse(Messages message) {
       this.sender_id = EncryptIds.encryptId(message.getSender_id());
       this.receiver_id = EncryptIds.encryptId(message.getReceiver_id());
       this.sent_date = message.getSent_date();
       this.message = message.getMessage();
    }

    public String getSender_id() {
        return sender_id;
    }

    public void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }

    public String getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(String receiver_id) {
        this.receiver_id = receiver_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getSent_date() {
        return sent_date;
    }

    public void setSent_date(Date sent_date) {
        this.sent_date = sent_date;
    }
}
