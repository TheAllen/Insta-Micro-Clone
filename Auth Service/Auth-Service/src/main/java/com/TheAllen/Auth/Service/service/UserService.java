package com.TheAllen.Auth.Service.service;

import com.TheAllen.Auth.Service.messaging.UserEventSender;
import com.TheAllen.Auth.Service.models.User;
import com.TheAllen.Auth.Service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserEventSender userEventSender;

    //For admin
    public List<User> findAll() {
        logger.info("Retrieving all users");
        return userRepository.findAll();
    }

    public Optional<User> findByUsername(String username) {
        logger.info("Retrieving user by username");
        return userRepository.findByUsername(username);
    }

    public List<User> findByUsernameIn(List<String> usernames) {
        logger.info("Retrieving list of users by username in");
        return userRepository.findByUsernameIn(usernames);
    }

    public User registerUser(User user) {

    }

    public User updateUser(User user) {

    }

    public User updateProfilePicture(String uri, String id) {

    }

}
