package com.intersect.poc.springcloudsub;


import com.intersect.poc.springcloudsub.channels.UserMessageChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@Slf4j
@EnableBinding({UserMessageChannel.class})
@SpringBootApplication
public class SpringCloudSubscriberApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudSubscriberApplication.class, "--management.endpoints.web.exposure.include=bindings");
	}
}