package com.example.spring.course.service.dto;

import lombok.Data;

@Data
public class UserRequestDto {
    private Long id;
    private String nome;
    private String email;
    private int eta;
    private String indirizzo;
}
