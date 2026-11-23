package com.example.spring.course.service;

import com.example.spring.course.exceptions.AppException;
import com.example.spring.course.model.request.OrderRequest;
import com.example.spring.course.model.request.ProductRequest;
import com.example.spring.course.model.response.OrderResponse;
import com.example.spring.course.model.response.ProductResponse;
import com.example.spring.course.repository.OrderRepository;
import com.example.spring.course.repository.model.CustomerEntity;
import com.example.spring.course.repository.model.OrderEntity;
import com.example.spring.course.repository.model.ProductDocument;
import com.example.spring.course.service.mapper.OrderMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;


@Service
@RequiredArgsConstructor
@Data
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper mapper;

    public OrderResponse findById(Long id) {
        OrderEntity orderEntity = orderRepository.findById(id).orElseThrow(() -> new AppException("Order: '" + id + "' not found", HttpStatus.NOT_FOUND));
        return mapper.toResponse(orderEntity);
    }

    @CachePut(value="orders")
    public OrderResponse createOrder(OrderRequest request) {
        OrderEntity order = new OrderEntity();

        order.getCustomer().setName(request.getNomeCliente());
        order.setTotal(request.getTotale());
        return mapper.toResponse(order);
    }

    @CachePut(value="orders", key="#id")
    public OrderResponse update(Long id, OrderRequest request) {
        OrderEntity order = orderRepository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Product not found: " + id));

        order.getCustomer().setName(request.getNomeCliente());


        OrderEntity updated = orderRepository.save(order);

        return mapper.toResponse(updated);
    }

    @CacheEvict(value="orders", key="#id")
    public void delete(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new ResponseStatusException(NOT_FOUND, "Product not found: " + id);
        }
        orderRepository.deleteById(id);
    }


}