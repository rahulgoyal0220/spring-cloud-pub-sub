package com.intersect.poc.springcloudsub;


import com.intersect.poc.springcloudsub.model.User;
import com.intersect.poc.springcloudsub.services.UserService;
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
@EnableBinding({SpringCloudSubscriberApplication.CloudStreamChannels.class})
@SpringBootApplication
public class SpringCloudSubscriberApplication {

	@Autowired
	private UserService userService;

	interface CloudStreamChannels {

		String TO_UPPERCASE_REPLY = "to-uppercase-reply";
		String TO_UPPERCASE_REQUEST = "to-uppercase-request";

		@Output(TO_UPPERCASE_REPLY)
		SubscribableChannel toUppercaseReply();

		@Input(TO_UPPERCASE_REQUEST)
		MessageChannel toUppercaseRequest();
	}

	public static void main(String[] args) {

		SpringApplication.run(SpringCloudSubscriberApplication.class, "--management.endpoints.web.exposure.include=bindings");
	}

	@StreamListener(CloudStreamChannels.TO_UPPERCASE_REQUEST)
	@SendTo(CloudStreamChannels.TO_UPPERCASE_REPLY)
	public Message<?> process(Message<String> request) {
		log.info("message recieved user id" + request.getPayload());
		log.info("Message header " + request.getHeaders());
		User user = userService.getUserById(Long.valueOf(request.getPayload()));
		return MessageBuilder.withPayload(user)
				.setHeader("__TypeId__", User.class.getName())
				.copyHeaders(request.getHeaders())
				.build();
	}

}