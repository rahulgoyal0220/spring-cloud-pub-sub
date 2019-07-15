package com.intersect.poc.springcloudsub.listener;

import com.intersect.poc.springcloudsub.channels.UserMessageChannel;
import com.intersect.poc.springcloudsub.model.User;
import com.intersect.poc.springcloudsub.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;

@Slf4j
@Configuration
public class UserMessageListener {

    @Autowired
    private UserService userService;

    @StreamListener(UserMessageChannel.USER_REQUEST)
    @SendTo(UserMessageChannel.USER_RESPONSE)
    public Message<?> process(Message<String> request) {
        log.info("message recieved user id" + request.getPayload());
        log.info("Message header " + request.getHeaders());
        User user = userService.getUserById(Long.valueOf(request.getPayload()));
        return MessageBuilder.withPayload(user)
                .setHeader("__TypeId__", User.class.getName())
                .copyHeaders(request.getHeaders())
                .build();
    }

}
