package com.TheAllen.Auth.Service.messaging;

import com.TheAllen.Auth.Service.models.User;
import com.TheAllen.Auth.Service.payload.UserEventPayload;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserEventSender {

    Logger logger = LoggerFactory.getLogger(UserEventSender.class);

    private UserEventStream channels;

    public UserEventSender(UserEventStream channels) {
        this.channels = channels;
    }

    private void sendUserChangedEvent(UserEventPayload payload) {
        Message<UserEventPayload> message = MessageBuilder.withPayload(payload)
                                                        .setHeader(KafkaHeaders.MESSAGE_KEY, payload.getId())
                                                        .build();

        //Send the message with the UserEventStream interface
        channels.instaUserChanged().send(message);

        logger.info("User event {} sent to topic {} for user {}",
                message.getPayload().getEventType().name(),
                channels.OUTPUT,
                message.getPayload().getUsername());
    }

    private UserEventPayload convertUserToPayload(User user, UserEventType eventType) {

        UserEventPayload payload = new UserEventPayload();
        payload.setEventType(eventType);
        payload.setId(user.getId());
        payload.setUsername(user.getUsername());
        payload.setEmail(user.getEmail());
        payload.setDisplayName(user.getProfile().getUsername());
        payload.setProfilePictureUrl(user.getProfile().getProfilePictureUrl());

        return payload;
    }

    //User Created
    public void sendUserCreated(User user) {
        logger.info("Send user created event. User {}", user.getUsername());
        sendUserChangedEvent(convertUserToPayload(user, UserEventType.CREATED));
    }

    //User Updated
    public void sendUserUpdated(User user) {
        logger.info("Send user updated event. User {}", user.getUsername());
        sendUserChangedEvent(convertUserToPayload(user, UserEventType.UPDATED));
    }

    //User Update the profile picture
    public void sendUserUpdated(User user, String oldPicUrl) {
        logger.info("Send user updated(profile picture) event for user {}", user.getUsername());
        UserEventPayload payload = convertUserToPayload(user, UserEventType.UPDATED);
        payload.setOldProfilePictureUrl(oldPicUrl);

        sendUserChangedEvent(payload);
    }

}
