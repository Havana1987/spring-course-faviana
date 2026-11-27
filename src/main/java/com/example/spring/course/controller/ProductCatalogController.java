package com.example.spring.course.controller;

import com.example.spring.course.model.request.ProductMongoRequest;
import com.example.spring.course.model.response.ProductMongoResponse;
import com.example.spring.course.service.ProductCatalogService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalog/products")
public class ProductCatalogController {

    private final ProductCatalogService service;


    public ProductCatalogController(ProductCatalogService service) {
        this.service = service;
    }


    @PostMapping("/admin/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductMongoResponse create(@Valid @RequestBody ProductMongoRequest request) {


        return service.create(request);
    }


    @GetMapping("/public/all")
    public List<ProductMongoResponse> findAll() {
        return service.findAll();
    }


    @GetMapping("/user/{id}")
    public ProductMongoResponse findById(@PathVariable String id) {
        return service.findById(id);
    }


    @PutMapping("/admin/update/{id}")
    public ProductMongoResponse update(
            @PathVariable String id,
            @Valid @RequestBody ProductMongoRequest request) {


        return service.update(id, request);
    }


    @DeleteMapping("/admin/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        service.delete(id);
    }


    @GetMapping("/user/category/{category}")
    public List<ProductMongoResponse> findByCategory(@PathVariable String category) {


        return service.findByCategory(category);
    }


    @GetMapping("/user/search")
    public List<ProductMongoResponse> searchByName(
            @RequestParam String name) {


        return service.searchByName(name);
    }
}
