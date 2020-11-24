package com.TheAllen.Auth.Service.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.SendTo;

public interface UserEventStream {

    String OUTPUT = "momentsUserChanged";

    @Output(value = OUTPUT)
    @StreamListener(Processor.OUTPUT)
    @SendTo(value = OUTPUT)
    MessageChannel momentsUserChanged();
}
