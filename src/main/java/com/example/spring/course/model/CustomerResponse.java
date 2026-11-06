package com.example.spring.course.model;

import com.example.spring.course.service.dto.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
public class CustomerResponse {
    private Long id;

    private String name;

    private List<OrderResponse> orders;
}
