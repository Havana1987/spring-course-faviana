package com.example.spring.course.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class OrderResponse {

    private Long id;

    private BigDecimal total;

    public OrderResponse(Long id, BigDecimal total) {
        this.id = id;
        this.total = total;
    }
}
