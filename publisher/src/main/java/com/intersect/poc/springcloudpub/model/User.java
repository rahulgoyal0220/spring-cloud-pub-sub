package com.intersect.poc.springcloudpub.model;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class User implements Serializable {

    private String name;
    private int age;
    private int telphone;
}
