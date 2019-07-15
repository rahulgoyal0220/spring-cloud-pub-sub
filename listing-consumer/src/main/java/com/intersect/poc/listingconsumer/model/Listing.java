package com.intersect.poc.listingconsumer.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@ToString
@Component
@Document(indexName = "property", type = "listing")
public class Listing {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private Long listingId;
    private String address;
    private String title;
    private String userId;
}
