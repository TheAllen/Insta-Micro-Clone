package com.TheAllen.Auth.Service.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserEventSender {

    private UserEventStream channels;

    public UserEventSender(UserEventStream channels) {
        this.channels = channels;
    }

}
