package com.example.spring.course.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;

public record ProductResponse(
        String id,

        String name,

        String description,

        BigDecimal price,

        String category,

        Instant createdAt,

        String brand,

        Map<String, Object> technicalDetails
) {
}
