package com.meetowin.meetowin.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "subscribed_users")
public class SubscribedUsers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

//    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

//    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Events event;

    public SubscribedUsers() {
    }

    public SubscribedUsers(Users user, Events event) {
        this.user = user;
        this.event = event;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Events getEvent() {
        return event;
    }

    public void setEvent(Events event) {
        this.event = event;
    }
}
