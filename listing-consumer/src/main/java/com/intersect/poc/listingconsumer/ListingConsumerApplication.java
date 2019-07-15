package com.intersect.poc.listingconsumer;

import com.intersect.poc.listingconsumer.model.Listing;
import com.intersect.poc.listingconsumer.service.ListingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;

@Slf4j
@EnableBinding({ListingConsumerApplication.ListingStreamChannel.class})
@SpringBootApplication
public class ListingConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ListingConsumerApplication.class, args);
	}

	interface ListingStreamChannel {
		String TO_LISTING_REPLY = "to-listing-reply";
		String TO_LISTING_REQUEST = "to-listing-request";

		@Output(TO_LISTING_REPLY)
		SubscribableChannel toListingReply();

		@Input(TO_LISTING_REQUEST)
		MessageChannel toListingRequest();
	}

	@Autowired
	private ListingService listingService;

	@StreamListener(ListingStreamChannel.TO_LISTING_REQUEST)
	@SendTo(ListingStreamChannel.TO_LISTING_REPLY)
	public Message<?> process(Message<String> request) {
		log.info("user id" + request.getPayload());
		Listing lsiting = listingService.getListingByUserId(request.getPayload());
		return MessageBuilder.withPayload(lsiting)
				.setHeader("__TypeId__", Listing.class.getName())
				.copyHeaders(request.getHeaders())
				.build();
	}
}
