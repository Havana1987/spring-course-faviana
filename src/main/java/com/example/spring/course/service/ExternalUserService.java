package com.example.spring.course.service;

import com.example.spring.course.client.ExternalUserClient;
import com.example.spring.course.model.response.ExternalUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExternalUserService {

    private final ExternalUserClient externalUserClient;

    public ExternalUserResponse findUser(Long id) {
        return externalUserClient.findUser(id);
    }
}
