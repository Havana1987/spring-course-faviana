package com.example.spring.course.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Map;

public record ProductRequest(
        @NotBlank
        String name,

        String description,

        @NotNull
        @DecimalMin("0.01")
        BigDecimal price,

        @NotBlank
        String category,


        String brand,


        Map<String, Object> technicalDetails
) {
}
