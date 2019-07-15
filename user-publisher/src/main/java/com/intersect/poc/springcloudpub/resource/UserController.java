package com.intersect.poc.springcloudpub.resource;


import com.intersect.poc.springcloudpub.gateway.UserMessageGateway;
import com.intersect.poc.springcloudpub.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserMessageGateway userMessageGateway;

    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") String string) {
        log.info("Message being sent from publiosher "+ string);
        return new ResponseEntity<>(userMessageGateway.process(string), HttpStatus.OK);
    }

}
