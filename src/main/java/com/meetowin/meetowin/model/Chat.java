package com.meetowin.meetowin.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "chat")
public class Chat {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    private Users sender;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "receiver_id", referencedColumnName = "id")
    private Users receiver;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "sent_date")
    private Date date;

    private String message;

    public Chat() {

    }

    public Chat(Long senderId, Long receiverId, String message) {
        this.sender = new Users(senderId);
        this.receiver = new Users(receiverId);
        this.message = message;
        this.date = new Date();
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Users getSender() {
        return sender;
    }

    public void setSender(Users sender) {
        this.sender = sender;
    }

    public Users getReceiver() {
        return receiver;
    }

    public void setReceiver(Users receiver) {
        this.receiver = receiver;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
