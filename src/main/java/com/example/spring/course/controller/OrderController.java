package com.example.spring.course.controller;

import com.example.spring.course.model.request.OrderRequest;
import com.example.spring.course.model.request.ProductRequest;
import com.example.spring.course.model.response.OrderResponse;
import com.example.spring.course.model.response.ProductResponse;
import com.example.spring.course.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;

    @PutMapping("/{id}")
    public OrderResponse update(
            @PathVariable Long id,
            @Valid @RequestBody OrderRequest request) {


        return service.update(id, request);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
