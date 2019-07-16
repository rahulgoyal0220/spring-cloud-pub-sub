package com.intersect.poc.listing;

import com.intersect.poc.listing.channel.ListingMessageChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@EnableBinding({ListingMessageChannel.class})
@SpringBootApplication
public class ListingPublisherApplication {

	public static void main(String[] args) {
		SpringApplication.run(ListingPublisherApplication.class, args);
	}
}
