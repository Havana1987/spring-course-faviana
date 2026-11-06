package com.example.spring.course.repository;

import com.example.spring.course.repository.model.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

}
