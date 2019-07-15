package com.intersect.poc.listingconsumer.repo;

import com.intersect.poc.listingconsumer.model.Listing;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListingRepository extends ElasticsearchRepository<Listing, String> {

    Listing getListingByUserId(String userId);
}
