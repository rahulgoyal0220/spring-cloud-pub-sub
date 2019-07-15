package com.intersect.poc.listingconsumer.service;

import com.intersect.poc.listingconsumer.model.Listing;

public interface ListingService {

    Listing getListingByUserId(String userId);
}
