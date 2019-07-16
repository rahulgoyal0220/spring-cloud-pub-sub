package com.intersect.poc.listing.resource;


import com.intersect.poc.listing.gateway.ListingMessageGateway;
import com.intersect.poc.listing.model.Listing;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserListingController {

    @Autowired
    ListingMessageGateway listingMessageGateway;

    @GetMapping("/listing/{id}")
    public ResponseEntity<Listing> getUser(@PathVariable("id") String string) {
        log.info("Message being "+ string);
        return new ResponseEntity<>(listingMessageGateway.process(string), HttpStatus.OK);
    }
}
