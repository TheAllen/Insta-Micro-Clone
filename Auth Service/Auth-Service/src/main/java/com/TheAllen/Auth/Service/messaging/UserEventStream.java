package com.TheAllen.Auth.Service.messaging;

import org.springframework.cloud.stream.annotation.*;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.MessageChannel;

public interface UserEventStream {

    String OUTPUT = "instaUserChanged";

    @Output(UserEventStream.OUTPUT)
    MessageChannel instaUserChanged();

}
