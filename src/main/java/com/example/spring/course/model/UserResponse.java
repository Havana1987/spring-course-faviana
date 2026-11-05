package com.example.spring.course.model;

import com.example.spring.course.service.dto.UserRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String messaggio;
    private Long id;
    private String nome;
    private String email;

    public UserResponse(String message, Long id, UserRequestDto user) {
        this.messaggio = message;
        this.id = id;
        this.nome = user.getNome();
        this.email = user.getEmail();
    }

    public UserResponse(String message) {
        this.messaggio = message;
    }

    public UserResponse(Long id) {
        this.id = id;
    }

    public UserResponse(String message, Long id) {
        this.messaggio = message;
        this.id = id;
    }

}
