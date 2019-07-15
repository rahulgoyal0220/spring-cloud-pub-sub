package com.intersect.poc.springcloudpub.gateway;

import com.intersect.poc.springcloudpub.channels.UserMessageChannel;
import com.intersect.poc.springcloudpub.config.IntegrationFlowConfig;
import com.intersect.poc.springcloudpub.model.User;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface UserMessageGateway {

    @Gateway(requestChannel = IntegrationFlowConfig.ENRICH, replyChannel = UserMessageChannel.USER_RESPONSE)
    User process(String payload);

}
