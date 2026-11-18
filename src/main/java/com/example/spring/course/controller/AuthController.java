package com.example.spring.course.controller;

import com.example.spring.course.model.request.RegisterRequest;
import com.example.spring.course.model.response.RegisterResponse;
import com.example.spring.course.service.RegistrationService;


import jakarta.validation.Valid;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private final RegistrationService registrationService;


    public AuthController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }


    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {


        RegisterResponse response = registrationService.register(request);


        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}