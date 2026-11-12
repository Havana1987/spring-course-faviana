package com.example.spring.course.controller;

import com.example.spring.course.model.ExternalUserResponse;
import com.example.spring.course.service.ExternalUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/external-users")
@RequiredArgsConstructor
public class ExternalUserController {

    private final ExternalUserService externalUserService;
    @GetMapping("/{id}")
    public ExternalUserResponse findUser(@PathVariable long id) {
        return externalUserService.findUser(id);
    }
}
