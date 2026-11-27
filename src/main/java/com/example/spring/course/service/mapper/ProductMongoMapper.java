package com.example.spring.course.service.mapper;

import com.example.spring.course.model.request.ProductMongoRequest;
import com.example.spring.course.model.response.ProductMongoResponse;
import com.example.spring.course.repository.model.ProductDocument;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class ProductMongoMapper {
    public ProductDocument toDocument(ProductMongoRequest request) {

        ProductDocument document = new ProductDocument();

        document.setName(request.name());
        document.setDescription(request.description());
        document.setPrice(request.price());
        document.setCategory(request.category());
        document.setCreatedAt(Instant.now());
        document.setBrand(request.brand());
        document.setTechnicalDetails(request.technicalDetails());

        return document;
    }

    public ProductMongoResponse toResponse(ProductDocument document) {
        return new ProductMongoResponse(
                document.getId(),
                document.getName(),
                document.getDescription(),
                document.getPrice(),
                document.getCategory(),
                document.getCreatedAt(),
                document.getBrand(),
                document.getTechnicalDetails()
        );
    }
}
