package com.intersect.poc.listingconsumer.service.impl;

import com.intersect.poc.listingconsumer.model.Listing;
import com.intersect.poc.listingconsumer.repo.ListingRepository;
import com.intersect.poc.listingconsumer.service.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListingServiceImpl implements ListingService {

    @Autowired
    private ListingRepository listingRepository;

    @Override
    public Listing getListingByUserId(String userId) {
        return listingRepository.getListingByUserId(userId);
    }
}
