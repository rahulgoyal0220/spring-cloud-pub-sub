package com.intersect.poc.listingconsumer.listeners;


import com.intersect.poc.listingconsumer.channel.ListingMessageChannel;
import com.intersect.poc.listingconsumer.model.Listing;
import com.intersect.poc.listingconsumer.service.ListingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;

@Slf4j
@Configuration
public class ListingListener {
    @Autowired
    private ListingService listingService;

    @StreamListener(ListingMessageChannel.LISTING_REQUEST)
    @SendTo(ListingMessageChannel.LISTING_RESPONSE)
    public Message<?> process(Message<String> request) {
        log.info("user id" + request.getPayload());
        Listing lsiting = listingService.getListingByUserId(request.getPayload());
        return MessageBuilder.withPayload(lsiting)
                .setHeader("__TypeId__", Listing.class.getName())
                .copyHeaders(request.getHeaders())
                .build();
    }

}
