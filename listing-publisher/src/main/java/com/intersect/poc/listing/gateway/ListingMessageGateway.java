package com.intersect.poc.listing.gateway;

import com.intersect.poc.listing.channel.ListingMessageChannel;
import com.intersect.poc.listing.model.Listing;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import static com.intersect.poc.listing.config.IntegrationFlowConfig.ENRICH;

@MessagingGateway
public interface ListingMessageGateway {

    @Gateway(requestChannel = ENRICH, replyChannel = ListingMessageChannel.LISTING_RESPONSE)
    Listing process(String payload);
}
