package com.intersect.poc.listingconsumer;

import com.intersect.poc.listingconsumer.channel.ListingMessageChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@Slf4j
@EnableBinding({ListingMessageChannel.class})
@SpringBootApplication
public class ListingConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ListingConsumerApplication.class, args);
	}
}
