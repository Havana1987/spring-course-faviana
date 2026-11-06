package com.example.spring.course.controller;

import com.example.spring.course.model.CustomerResponse;
import com.example.spring.course.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerId(@PathVariable Long id) {
        CustomerResponse customerResponse = customerService.findById(id);
        return new ResponseEntity<>(customerResponse, HttpStatus.OK);
    }
}
