package com.intersect.poc.springcloudpub;

import com.intersect.poc.springcloudpub.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
@EnableBinding({SpringCloudPubApplication.GatewayChannels.class})
@SpringBootApplication
public class SpringCloudPubApplication {

	interface GatewayChannels {

		String TO_UPPERCASE_REPLY = "to-uppercase-reply";
		String TO_UPPERCASE_REQUEST = "to-uppercase-request";

		@Input(TO_UPPERCASE_REPLY)
		SubscribableChannel toUppercaseReply();

		@Output(TO_UPPERCASE_REQUEST)
		MessageChannel toUppercaseRequest();
	}

	@MessagingGateway
	public interface StreamGateway {
		@Gateway(requestChannel = ENRICH, replyChannel = GatewayChannels.TO_UPPERCASE_REPLY)
		User process(String payload);
	}

	private static final String ENRICH = "enrich";

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudPubApplication.class, args);
	}

	@Bean
	public IntegrationFlow headerEnricherFlow() {
		return IntegrationFlows.from(ENRICH).enrichHeaders(HeaderEnricherSpec::headerChannelsToString)
				.channel(GatewayChannels.TO_UPPERCASE_REQUEST).get();
	}

	@Bean
	public IntegrationFlow payLoadData() {
		return IntegrationFlows.from(GatewayChannels.TO_UPPERCASE_REPLY)
				.convert(User.class)
				.channel(GatewayChannels.TO_UPPERCASE_REPLY).get();
	}

	@RestController
	public class UppercaseController {
		@Autowired
		StreamGateway gateway;

		@GetMapping(value = "/string/{string}")
		public ResponseEntity<User> getUser(@PathVariable("string") String string) {
			log.info("Message being sent from publiosher "+ string);
			//gateway.process(string);
			return new ResponseEntity<>(gateway.process(string), HttpStatus.OK);
		}
	}

}
