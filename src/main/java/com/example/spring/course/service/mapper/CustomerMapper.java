package com.example.spring.course.service.mapper;

import com.example.spring.course.model.CustomerResponse;
import com.example.spring.course.model.OrderResponse;
import com.example.spring.course.repository.model.CustomerEntity;
import com.example.spring.course.service.dto.CustomerDto;
import com.example.spring.course.service.dto.OrderDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerMapper {

    public CustomerResponse toResponse(CustomerDto dto) {
        List<OrderResponse> orders = dto.getOrders()
                .stream()
                .map( orderDto -> new OrderResponse(
                        orderDto.getId(), orderDto.getTotal()
                ))
                .toList();

        return new CustomerResponse(
                dto.getId(), dto.getName(), orders
        );
    }

    public CustomerDto toDto(CustomerEntity customerEntity) {
        List<OrderDto> orders = customerEntity.getOrders()
                .stream()
                .map( orderDto -> new OrderDto(
                        orderDto.getId(), orderDto.getTotal()
                ))
                .toList();

        return new CustomerDto(
                customerEntity.getId(), customerEntity.getName(), orders
        );
    }
}
