package com.meetowin.meetowin.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(unique=true,nullable = false)
    private String username;
    @Column(unique=true,nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(name = "phone_number",nullable = false)
    private String phoneNumber;

    private Date date;
    @Column(name = "reported_number",nullable = false)
    private Long reportedNumber=0L;

    public Users() {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getReportedNumber() {
        return reportedNumber;
    }

    public void setReportedNumber(Long reportedNumber) {
        this.reportedNumber = reportedNumber;
    }
}
