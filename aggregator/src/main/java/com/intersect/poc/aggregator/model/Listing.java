package com.intersect.poc.aggregator.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@ToString
@Component
public class Listing {

    private static final long serialVersionUID = 1L;

    private String id;
    private Long listingId;
    private String address;
    private String title;
    private String userId;
}
