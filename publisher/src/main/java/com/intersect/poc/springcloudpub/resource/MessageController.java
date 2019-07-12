package com.intersect.poc.springcloudpub.resource;

import com.intersect.poc.springcloudpub.SpringCloudPubApplication;
import com.intersect.poc.springcloudpub.channels.UserMessageChannel;
import com.intersect.poc.springcloudpub.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessageController {


    /*   @Autowired
        SpringCloudPubApplication.StreamGateway gateway;

        @GetMapping(value = "/string/{string}",
                produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
        public ResponseEntity<User> getUser(@PathVariable("string") String string) {
            return new ResponseEntity<>(gateway.process(string), HttpStatus.OK);
        }*/

}
