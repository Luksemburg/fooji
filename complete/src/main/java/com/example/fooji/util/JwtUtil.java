package com.example.fooji.util;

import com.example.fooji.entity.User;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.util.Date;


@Service
public class JwtUtil {
    private static final String SECRET_KEY = "uPqvJQFJzvJhxLXTJ7Wz6DHsa1mR1XNUhz7iIa3OKh8=";  // keep safe!

    public String generateToken(User user) {
        //long expirationTime = 1000 * 60 * 60 * 10; // 10 hours

        return Jwts.builder()
                .setSubject(user.getUsername())
                //.claim("role", user.getRole())
                .claim("id", user.getId())
                .setIssuedAt(new Date())
                //.setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }
}
