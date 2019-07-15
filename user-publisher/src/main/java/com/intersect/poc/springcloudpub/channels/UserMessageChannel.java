package com.intersect.poc.springcloudpub.channels;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface UserMessageChannel {

    String USER_RESPONSE="user-response";
    String USER_REQUEST = "user-request";

    @Input(USER_RESPONSE)
    SubscribableChannel userResponse();

    @Output(USER_REQUEST)
    MessageChannel userRequest();
}
