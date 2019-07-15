package com.intersect.poc.springcloudpub;

import com.intersect.poc.springcloudpub.channels.UserMessageChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@Slf4j
@EnableBinding({UserMessageChannel.class})
@SpringBootApplication
public class SpringCloudPubApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudPubApplication.class, args);
	}


}
