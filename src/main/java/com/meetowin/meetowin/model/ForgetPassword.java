package com.meetowin.meetowin.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "forget_password")
public class ForgetPassword {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String code;
    private Date expiration ;
    private boolean valid =true;
    private String email;
    private String token;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private Date createdDate;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="user_id", nullable=false)
    private Users users;

    public ForgetPassword(){
    }

    public ForgetPassword(Long id, String code, Date expiration, boolean valid, String email, String token, Date createdDate, Users users) {
        this.id = id;
        this.code = code;
        this.expiration = expiration;
        this.valid = valid;
        this.email = email;
        this.token = token;
        this.createdDate = createdDate;
        this.users = users;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
