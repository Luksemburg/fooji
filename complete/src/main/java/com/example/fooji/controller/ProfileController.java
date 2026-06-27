package com.example.fooji.controller;

import com.example.fooji.entity.User;
import com.example.fooji.entity.UserDTO;
import com.example.fooji.filter.CustomUserDetails;
import com.example.fooji.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private static final Logger log = LoggerFactory.getLogger(ProfileController.class);

    @Autowired
    UserService userService;

    /* TODO
     password hash
     check the old password
     gender does not work
     ID does not save on the client, so the user can edit only once
     format check for all editable values
     */
    @PostMapping("/save")
    public User save(@RequestBody UserDTO userDTO, @AuthenticationPrincipal CustomUserDetails customUser) {

        Authentication auth =
                SecurityContextHolder.getContext().getAuthentication();

        log.info(" ==== ProfileController:auth ==== {}", auth);
        log.info(" ==== ProfileController:userDTO ==== {}", userDTO);

        User user = userService.findUserById(customUser.getId());
        if(userDTO.getPassword() != null && !userDTO.getPassword().isBlank()) {
            user.setPassword(userDTO.getPassword());
        }
        if(userDTO.getUsername() != null && !userDTO.getUsername().isBlank()) {
            user.setUsername(userDTO.getUsername());
        }
        if(userDTO.getPhone() != null) {
            user.setPhone(userDTO.getPhone());
        }
        if(userDTO.getLocation() != null && !userDTO.getLocation().isBlank()) {
            user.setLocation(userDTO.getLocation());
        }
        if(userDTO.getGender() != null && !userDTO.getGender().isBlank()) {
            user.setGender(userDTO.getGender());
        }
        //user.setEmail(userDTO.getEmail());

        try {
            userService.save(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return(user);
    }
}
