package com.TheAllen.Auth.Service.service;

import com.TheAllen.Auth.Service.messaging.UserEventSender;
import com.TheAllen.Auth.Service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;
    private UserEventSender userEventSender;
}
