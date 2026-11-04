package com.example.spring.course.service.impl;

import com.example.spring.course.service.dto.UserResponseDto;

import java.util.List;
import java.util.UUID;

public interface UserServiceImpl {
    UserResponseDto getUserById(UUID id);
}
