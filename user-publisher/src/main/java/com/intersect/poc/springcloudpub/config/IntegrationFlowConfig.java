package com.intersect.poc.springcloudpub.config;

import com.intersect.poc.springcloudpub.channels.UserMessageChannel;
import com.intersect.poc.springcloudpub.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.HeaderEnricherSpec;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;

@Configuration
public class IntegrationFlowConfig {

    public static final String ENRICH = "enrich";

    @Bean
    public IntegrationFlow headerEnricherFlow() {
        return IntegrationFlows.from(ENRICH).enrichHeaders(HeaderEnricherSpec::headerChannelsToString)
                .channel(UserMessageChannel.USER_REQUEST).get();
    }

    @Bean
    public IntegrationFlow payLoadData() {
        return IntegrationFlows.from(UserMessageChannel.USER_RESPONSE)
                .convert(User.class)
                .channel(UserMessageChannel.USER_RESPONSE).get();
    }
}
