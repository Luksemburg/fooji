package com.example.fooji.filter;

import com.example.fooji.controller.ProfileController;
import com.example.fooji.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthFilter.class);

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {

            String token = header.substring(7);

            try {
                Claims claims = jwtUtil.parseToken(token);

                log.info(" ==== Claims ==== {}", claims);

                Long userId = Long.valueOf(claims.get("id", Integer.class));
                String email = claims.get("email", String.class);

                CustomUserDetails userDetails =
                        new CustomUserDetails(userId, email);

                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                //log.info(" ==== JwtAuthFilter:auth ==== {}", auth);

                SecurityContextHolder.getContext().setAuthentication(auth);

            } catch (Exception e) {
                e.printStackTrace();
                // invalid token → ignore or log
            }
        }

        filterChain.doFilter(request, response);
    }
}
