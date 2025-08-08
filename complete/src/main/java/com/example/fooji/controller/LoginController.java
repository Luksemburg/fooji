package com.example.fooji.controller;

import com.example.fooji.entity.LoginRequest;
import com.example.fooji.entity.LoginResponse;
import com.example.fooji.entity.User;
import com.example.fooji.service.LoginService;
import com.example.fooji.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    private final LoginService loginService;
    private final JwtUtil jwtUtil;

    public LoginController(LoginService loginService, JwtUtil jwtUtil){
        this.loginService = loginService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        User user = loginService.authenticate(loginRequest);
        String token = jwtUtil.generateToken(user);

        return ResponseEntity.ok(new LoginResponse(token, user));
    }
}
