package com.example.fooji.service;

import com.example.fooji.entity.LoginRequest;
import com.example.fooji.entity.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LoginService {

    private static final Logger log = LoggerFactory.getLogger(LoginService.class);

    @ManyToOne
    @JoinColumn(name = "user_id")
    User mockUser = new User();

    public User authenticate(LoginRequest request) {
        //query from DB
        if ("user".equals(request.getUsername()) && "pass".equals(request.getPassword())) {
            return new User();
        }

        mockUser.setActive(true);
        mockUser.setEmail("test@test.mail");
        mockUser.setGender("male");
        mockUser.setId(788L);
        mockUser.setLocation("Tokyo");
        mockUser.setNotify(true);
        mockUser.setPhone(380505064889L);
        mockUser.setUsername("Test User");
        mockUser.setLastLoginAt(LocalDateTime.now());
        mockUser.setCreatedAt(LocalDateTime.now());
        mockUser.setUpdatedAt(LocalDateTime.now());

        return mockUser;

        //throw new RuntimeException("Invalid username or password");
    }

}
