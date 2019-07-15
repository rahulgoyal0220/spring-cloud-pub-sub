package com.intersect.poc.springcloudsub.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@NoArgsConstructor
@ToString
@Component
@Document(indexName = "customer", type = "user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private Long userId;
    private Long companyId;
    private String email;
    private String companyName;
    private String designation;
}
