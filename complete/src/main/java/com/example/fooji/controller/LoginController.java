package com.example.fooji.controller;

import com.example.fooji.entity.*;
import com.example.fooji.service.LoginService;
import com.example.fooji.service.UserService;
import com.example.fooji.util.JwtUtil;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.util.Collections;

@RestController
@RequestMapping("/login")
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    private final LoginService loginService;
    private final UserService userService;
    private final JwtUtil jwtUtil;

        public LoginController(LoginService loginService, JwtUtil jwtUtil, UserService userService){
        this.loginService = loginService;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        User user = loginService.authenticate(loginRequest);
        String token = jwtUtil.generateToken(user);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        userDTO.setLocation(user.getLocation());
        userDTO.setGender(user.getGender());

        return ResponseEntity.ok().headers(headers).body(userDTO);
    }

    @PostMapping("/googleLogin")
    public ResponseEntity<?> googleLogin(@RequestParam String clientId) {

        log.info(" ===== googleLogin ===== ");
        //log.info(" ===== Client Request: " + googleId + " ===== " );
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                new NetHttpTransport(),
                new JacksonFactory())
                .setAudience(Collections
                        .singletonList("212337228143-6djukr8r7iromq22cv5g74434bu381gl.apps.googleusercontent.com"))
                .build();

        GoogleIdToken idToken;
        try {
            idToken = verifier.verify(clientId);
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }

        if (idToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }

        GoogleIdToken.Payload payload = idToken.getPayload();

        log.info(" ===== PayLoad ===== {}", payload);

        String googleId = payload.getSubject();   // stable ID
        String email = payload.getEmail();
        String name = (String) payload.get("name");
        Boolean emailVerified = payload.getEmailVerified();

        if (!Boolean.TRUE.equals(emailVerified)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email not verified");
        }

        User user = userService.findByGoogleId(googleId);

        if (user == null) {
            user = new User();
            user.setGoogleId(googleId);
            user.setEmail(email);
            user.setUsername(name);
            user.setNotify(true);
            user.setActive(true);
            user.setPassword("mock");
            user.setCreatedAt(LocalDateTime.now());

            userService.createUser(user);
        }

        String jwt = jwtUtil.generateToken(user);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + jwt);

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(name);
        userDTO.setEmail(email);
        userDTO.setGoogleId(googleId);

        return ResponseEntity.ok().headers(headers).body(userDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User requestUser) {
        User user = userService.createUser(requestUser);
        String token = jwtUtil.generateToken(user);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);

        return ResponseEntity.ok().headers(headers).body(new LoginResponse(token, user));
    }



}
