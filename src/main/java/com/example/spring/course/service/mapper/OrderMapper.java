package com.example.spring.course.service.mapper;

import com.example.spring.course.model.request.OrderRequest;
import com.example.spring.course.model.response.OrderResponse;
import com.example.spring.course.repository.model.CustomerEntity;
import com.example.spring.course.repository.model.OrderEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public OrderResponse toResponse(OrderEntity entity) {
        return new OrderResponse(
                entity.getId(), entity.getTotal()
        );
    }

}
