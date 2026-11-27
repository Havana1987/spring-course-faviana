package com.example.spring.course.repository;

import com.example.spring.course.repository.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}