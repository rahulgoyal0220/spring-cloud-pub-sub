package com.intersect.poc.listingconsumer.resource;

import com.intersect.poc.listingconsumer.model.Listing;
import com.intersect.poc.listingconsumer.repo.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ListingController {

    @Autowired
    private ListingRepository listingRepository;

    @PostMapping(value = "/userlisting")
    public Listing createUserListing(@RequestBody Listing listing){
        return listingRepository.save(listing);

    }
}
