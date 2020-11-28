package com.TheAllen.Auth.Service.messaging;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserEventSender {

    Logger logger = LoggerFactory.getLogger(UserEventSender.class);

    private UserEventStream channels;

    public UserEventSender(UserEventStream channels) {
        this.channels = channels;
    }

}
