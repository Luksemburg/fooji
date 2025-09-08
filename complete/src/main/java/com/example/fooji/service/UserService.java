package com.example.fooji.service;

import com.example.fooji.entity.User;
import com.example.fooji.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public User createUser(User requestUser) {
        userRepository.save(requestUser);
        return requestUser;
    }
}
