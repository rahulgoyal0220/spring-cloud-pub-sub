package com.intersect.poc.listing.config;

import com.intersect.poc.listing.channel.ListingMessageChannel;
import com.intersect.poc.listing.model.Listing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.HeaderEnricherSpec;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;

@Configuration
public class IntegrationFlowConfig {

    @Bean
    public IntegrationFlow headerEnricherFlow() {
        return IntegrationFlows.from(ENRICH).enrichHeaders(HeaderEnricherSpec::headerChannelsToString)
                .channel(ListingMessageChannel.LISTING_REQUEST).get();
    }

    public static final String ENRICH = "enrich";


    @Bean
    public IntegrationFlow payLoadData() {
        return IntegrationFlows.from(ListingMessageChannel.LISTING_RESPONSE)
                .convert(Listing.class)
                .channel(ListingMessageChannel.LISTING_RESPONSE).get();
    }
}
