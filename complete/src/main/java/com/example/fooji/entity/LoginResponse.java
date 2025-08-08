package com.example.fooji.entity;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;


public class LoginResponse {

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;*/

    private String token;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public LoginResponse(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() { return token; }
    public User getUser() { return user; }
}
