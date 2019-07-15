package com.intersect.poc.springcloudpub.transformer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intersect.poc.springcloudpub.model.User;
import org.springframework.messaging.Message;
import org.springframework.messaging.converter.AbstractMessageConverter;
import org.springframework.util.MimeType;

import java.io.IOException;

public class UserMessageConverter extends AbstractMessageConverter {

    public UserMessageConverter() {
        super(new MimeType("application", "user"));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return (User.class == clazz);
    }

    @Override
    protected Object convertFromInternal(Message<?> message, Class<?> targetClass, Object conversionHint) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue((byte[]) message.getPayload(), User.class);
        } catch (IOException e) {
            return null;
        }
    }
}
