package com.example.spring.course.service;

import com.example.spring.course.model.request.RegisterRequest;
import com.example.spring.course.model.response.RegisterResponse;
import com.example.spring.course.repository.UserAccountRepository;
import com.example.spring.course.repository.model.UserAccount;


import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;


@Service
public class RegistrationService {


    private final UserAccountRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public RegistrationService(
            UserAccountRepository userRepository,
            PasswordEncoder passwordEncoder) {


        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Transactional
    public RegisterResponse register(RegisterRequest request) {


        if (userRepository.existsByUsername(request.username())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Username già utilizzato"
            );
        }


        UserAccount user = new UserAccount(
                request.username(),
                passwordEncoder.encode(request.password()),
                "USER"
        );


        UserAccount savedUser = userRepository.save(user);


        return new RegisterResponse(savedUser.getUsername(),
                savedUser.getRole()
        );
    }
}