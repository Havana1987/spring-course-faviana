package com.example.spring.course.model;

import com.example.spring.course.repository.model.CustomerEntity;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Order {
    private Long id;

    private BigDecimal total;

    private CustomerEntity customer;
}
