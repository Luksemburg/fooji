package com.example.fooji.controller;

import com.example.fooji.entity.User;
import com.example.fooji.entity.UserDTO;
import com.example.fooji.filter.CustomUserDetails;
import com.example.fooji.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private static final Logger log = LoggerFactory.getLogger(ProfileController.class);

    @Autowired
    UserService userService;

    //TODO check signature, password hash, should be email editable?
    @PostMapping("/save")
    public Boolean save(UserDTO userDTO, @AuthenticationPrincipal CustomUserDetails customUser) {

        User user = userService.findUserById(customUser.getId());
        user.setPassword(userDTO.getPassword());
        user.setUsername(userDTO.getUsername());
        user.setPhone(userDTO.getPhone());
        user.setLocation(userDTO.getLocation());
        user.setGender(userDTO.getGender());
        user.setEmail(userDTO.getEmail());

        return(userService.save(user));
    }
}
