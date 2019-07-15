package com.intersect.poc.springcloudpub.model;



import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@NoArgsConstructor
@ToString
@Component
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private Long userId;
    private Long companyId;
    private String email;
    private String companyName;
    private String designation;
}