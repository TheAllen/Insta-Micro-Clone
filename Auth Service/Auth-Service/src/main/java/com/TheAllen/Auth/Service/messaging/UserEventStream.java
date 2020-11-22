package com.TheAllen.Auth.Service.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface UserEventStream {

    String OUTPUT = "momentsUserChanged";

    @Output(value = OUTPUT)
    MessageChannel momentsUserChanged();
}
