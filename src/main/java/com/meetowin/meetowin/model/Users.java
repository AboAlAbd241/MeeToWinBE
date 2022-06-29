package com.meetowin.meetowin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.apache.catalina.User;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique=true)
    private String username;

    @Email
    @Column(unique=true,nullable = false)
    private String email;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(nullable = false)
    private Boolean emailVerified = false;

    @JsonIgnore
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    @Column(name = "provider_id")
    private String providerId;

    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "reported_number",nullable = false)
    private Long reportedNumber=0L;

    @JsonManagedReference
    @OneToMany(mappedBy="users")
    @JsonIgnore
    private List<ForgetPassword> forgetPassword;

//    @ManyToMany(mappedBy = "subscribedUsers")
//    private List<Events> subscribedEvents;

    private String about;

    private Long rating;

    @Column(name = "event_created")
    private Long eventCreated;

//    @JsonManagedReference
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<SubscribedUsers> subscribedUsers;

    @JsonIgnore
    @OneToMany(mappedBy = "sender")
    private List<Chat> sender;

    @JsonIgnore
    @OneToMany(mappedBy = "receiver")
    private List<Chat> receiver;


    public Users() {
    }

    public Users(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public AuthProvider getProvider() {
        return provider;
    }

    public void setProvider(AuthProvider provider) {
        this.provider = provider;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public Date getDate() {
        return createdDate;
    }

    public void setDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getReportedNumber() {
        return reportedNumber;
    }

    public void setReportedNumber(Long reportedNumber) {
        this.reportedNumber = reportedNumber;
    }

    public List<ForgetPassword> getForgetPassword() {
        return forgetPassword;
    }

    public void setForgetPassword(List<ForgetPassword> forgetPassword) {
        this.forgetPassword = forgetPassword;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

//    public List<Events> getSubscribedEvents() {
//        return subscribedEvents;
//    }
//
//    public void setSubscribedEvents(List<Events> rolledEvents) {
//        this.subscribedEvents = rolledEvents;
//    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public Long getEventCreated() {
        return eventCreated;
    }

    public void setEventCreated(Long eventCreated) {
        this.eventCreated = eventCreated;
    }

    public List<SubscribedUsers> getSubscribedUsers() {
        return subscribedUsers;
    }

    public void setSubscribedUsers(List<SubscribedUsers> subscribedUsers) {
        this.subscribedUsers = subscribedUsers;
    }
}
