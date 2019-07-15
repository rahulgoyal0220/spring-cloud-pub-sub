package com.intersect.poc.springcloudsub.channels;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface UserMessageChannel {

     String USER_RESPONSE="user-response";
     String USER_REQUEST = "user-request";

    @Output(USER_RESPONSE)
    SubscribableChannel userResponse();

    @Input(USER_REQUEST)
    MessageChannel userRequest();
}
