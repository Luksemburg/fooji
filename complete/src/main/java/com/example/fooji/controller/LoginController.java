package com.example.fooji.controller;

import com.example.fooji.entity.LoginRequest;
import com.example.fooji.entity.LoginResponse;
import com.example.fooji.entity.User;
import com.example.fooji.service.LoginService;
import com.example.fooji.service.UserService;
import com.example.fooji.util.JwtUtil;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        return ResponseEntity.ok().headers(headers).body(new LoginResponse(token, user));
    }

    @PostMapping("/googleLogin")
    public ResponseEntity<?> googleLogin(@RequestBody LoginRequest loginRequest) {

        /*GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                new NetHttpTransport(),
                new JacksonFactory())
                .setAudience(Collections.singletonList("YOUR_WEB_CLIENT_ID"))
                .build();

        GoogleIdToken idToken = verifier.verify(loginRequest.getID-TOKEN);*/
        return null;

        /*User user = loginService.authenticate(loginRequest);
        String token = jwtUtil.generateToken(user);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);

        return ResponseEntity.ok().headers(headers).body(new LoginResponse(token, user));*/

        /*log.info("===== googleLogin ===== {}", request.toString());
        return null;*/
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
