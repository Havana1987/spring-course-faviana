package com.example.spring.course.model.request;

import com.example.spring.course.model.Order;
import lombok.Data;

import java.util.List;

@Data
public class CustomerRequest {
    private Long id;
    private String name;
    private List<Order> orders;
}
