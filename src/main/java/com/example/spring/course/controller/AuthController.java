package com.example.spring.course.controller;

import com.example.spring.course.model.request.LoginRequest;
import com.example.spring.course.model.request.RegisterRequest;
import com.example.spring.course.model.response.RegisterResponse;
import com.example.spring.course.model.response.TokenResponse;
import com.example.spring.course.service.RegistrationService;


import com.example.spring.course.service.TokenService;
import jakarta.validation.Valid;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private final RegistrationService registrationService;

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;


    public AuthController(RegistrationService registrationService, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.registrationService = registrationService;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }


    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {


        RegisterResponse response = registrationService.register(request);


        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest request) {

        try {
            var authentication = authenticationManager.authenticate(UsernamePasswordAuthenticationToken.unauthenticated(request.getUsername(), request.getPassword()));
            return ResponseEntity.ok().header("Cache-Control", "no-store")
                    .body(tokenService.issue(authentication));
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenziali non valide");
        }



    }
}