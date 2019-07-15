package com.intersect.poc.aggregator;

import com.intersect.poc.aggregator.model.Listing;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.dsl.HeaderEnricherSpec;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@EnableBinding({AggregatorApplication.ListingStreamChannel.class})
@SpringBootApplication
public class AggregatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(AggregatorApplication.class, args);
	}

	interface ListingStreamChannel {

		String TO_LISTING_REPLY = "to-listing-reply";
		String TO_LISTING_REQUEST = "to-listing-request";

		@Input(TO_LISTING_REPLY)
		SubscribableChannel toListingReply();

		@Output(TO_LISTING_REQUEST)
		MessageChannel toListingRequest();
	}

	@MessagingGateway
	public interface ListingGateway {
		@Gateway(requestChannel = ENRICH, replyChannel = ListingStreamChannel.TO_LISTING_REPLY)
		Listing process(String payload);
	}

	@Bean
	public IntegrationFlow headerEnricherFlow() {
		return IntegrationFlows.from(ENRICH).enrichHeaders(HeaderEnricherSpec::headerChannelsToString)
				.channel(ListingStreamChannel.TO_LISTING_REQUEST).get();
	}

	private static final String ENRICH = "enrich";


	@Bean
	public IntegrationFlow payLoadData() {
		return IntegrationFlows.from(ListingStreamChannel.TO_LISTING_REPLY)
				.convert(Listing.class)
				.channel(ListingStreamChannel.TO_LISTING_REPLY).get();
	}

	@RestController
	public class UserListingController {
		@Autowired
		ListingGateway gateway;

		@GetMapping("/userlisting/{id}")
		public ResponseEntity<Listing> getUser(@PathVariable("id") String string) {
			log.info("Message being "+ string);
			return new ResponseEntity<>(gateway.process(string), HttpStatus.OK);
		}
	}
}
