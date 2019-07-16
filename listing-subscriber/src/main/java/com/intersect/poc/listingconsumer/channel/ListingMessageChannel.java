package com.intersect.poc.listingconsumer.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface ListingMessageChannel {

    String LISTING_RESPONSE = "listing-response";
    String LISTING_REQUEST = "listing-request";

    @Output(LISTING_RESPONSE)
    SubscribableChannel listingResponse();

    @Input(LISTING_REQUEST)
    MessageChannel listingRequest();
}
