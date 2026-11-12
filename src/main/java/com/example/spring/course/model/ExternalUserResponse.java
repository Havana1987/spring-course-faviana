package com.example.spring.course.model;

import lombok.Data;

@Data
public class ExternalUserResponse {

    private Long id;
    private String name;
    private String username;
    private String email;
}
