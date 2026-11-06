package com.example.spring.course.service;

import com.example.spring.course.exceptions.AppException;
import com.example.spring.course.model.CustomerResponse;
import com.example.spring.course.repository.CustomerRepository;
import com.example.spring.course.repository.model.CustomerEntity;
import com.example.spring.course.service.dto.CustomerDto;
import com.example.spring.course.service.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;

    @Transactional(readOnly = true)
    public CustomerResponse findById(Long id) {
        CustomerEntity customerEntity = customerRepository.findById(id).orElseThrow(() -> new AppException("Customer: '" + id + "' not found", HttpStatus.NOT_FOUND));
        CustomerDto customerDto = mapper.toDto(customerEntity);
        return mapper.toResponse(customerDto);
    }

}
