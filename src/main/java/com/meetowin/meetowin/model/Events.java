package com.meetowin.meetowin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "events")
public class Events {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    private String price;

    @OneToOne(targetEntity = Users.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private Users user;

    private String location;

    @Column(name = "num_of_player")
    private int numOfPlayers;

    @Column(name = "image_path")
    private String imagePath;

    private String duration;

//    @JsonManagedReference
    @JsonIgnore
    @OneToMany(mappedBy = "event")
    private List<SubscribedUsers> subscribedUsersEvent;

    public Events() {
    }

    public Events(Long id) {
        this.id = id;
    }

    public Events(Long id, String title, String description, Date date, String price, Users user, String location, int numOfPlayers, String imagePath, String duration) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.price = price;
        this.user = user;
        this.location = location;
        this.numOfPlayers = numOfPlayers;
        this.imagePath = imagePath;
        this.duration = duration;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        price = price;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public void setNumOfPlayers(int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }


    public List<SubscribedUsers> getSubscribedUsersEvent() {
        return subscribedUsersEvent;
    }

    public void setSubscribedUsersEvent(List<SubscribedUsers> subscribedUsersEvent) {
        this.subscribedUsersEvent = subscribedUsersEvent;
    }

    @Override
    public String toString() {
        return "Events{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", Price='" + price + '\'' +
                ", user=" + user +
                ", location='" + location + '\'' +
                ", numOfPlayers=" + numOfPlayers +
                ", imagePath='" + imagePath + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
