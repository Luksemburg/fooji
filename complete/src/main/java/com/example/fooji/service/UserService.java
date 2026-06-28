package com.example.fooji.service;

import com.example.fooji.entity.User;
import com.example.fooji.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(User requestUser) {
        requestUser.setPassword(passwordEncoder.encode(requestUser.getPassword()));
        userRepository.save(requestUser);
        return requestUser;
    }

    public User findByGoogleId(String googleId) {
        return userRepository.findByGoogleId(googleId);
    }

    public void save(User user) {
        try{

            user.setPassword(passwordEncoder.encode(user.getPassword()));

            log.info(" ==== User.save ==== {}", user);

            /*User userToUpdate = userRepository.findUserById(user.getId());
            userToUpdate.setUsername(user.getUsername());
            userToUpdate.setPhone(user.getPhone());
            userToUpdate.setLocation(user.getLocation());
            userToUpdate.setGender(user.getGender());
            userToUpdate.setPassword(user.getPassword());
            userRepository.save(userToUpdate);*/

            userRepository.save(user);
        }catch (Throwable t){
            t.printStackTrace();
        }
    }

    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }
}
