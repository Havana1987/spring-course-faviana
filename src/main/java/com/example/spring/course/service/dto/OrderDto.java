package com.example.spring.course.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class OrderDto {

    private Long id;

    private BigDecimal total;

    public OrderDto(Long id, BigDecimal total) {
        this.id = id;
        this.total = total;
    }
}
