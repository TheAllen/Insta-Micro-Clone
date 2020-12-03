package com.TheAllen.Auth.Service.service;

import com.TheAllen.Auth.Service.exceptions.EmailAlreadyExistsException;
import com.TheAllen.Auth.Service.exceptions.UsernameAlreadyExistsException;
import com.TheAllen.Auth.Service.messaging.UserEventSender;
import com.TheAllen.Auth.Service.models.Role;
import com.TheAllen.Auth.Service.models.User;
import com.TheAllen.Auth.Service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

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

        logger.info("Register user {}", user.getUsername());

        if(userRepository.existsByUsername(user.getUsername())) {
            logger.warn("username {} already exists", user.getUsername());

            throw new UsernameAlreadyExistsException(
                    String.format("username %s already exists", user.getUsername()));
        }

        if(userRepository.existsByEmail(user.getEmail())) {
            logger.warn("email {} already exists", user.getEmail());

            throw new EmailAlreadyExistsException(
                String.format("email %s already exists", user.getEmail()));
        }

        user.setActive(true);
        //Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>() {{
            add(Role.USER);
        }});

        User newUser = userRepository.save(user);

        //Send Message
        userEventSender.sendUserCreated(newUser);

        return newUser;
    }

    public User updateUser(User user) {

    }

    public User updateProfilePicture(String uri, String id) {

    }

}
