package com.example.spring.course;

import com.example.spring.course.model.request.ProductRequest;
import com.example.spring.course.repository.ProductMongoRepository;
import com.example.spring.course.repository.model.ProductDocument;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureTestRestTemplate
//@Testcontainers
@ActiveProfiles("mongo")
public class ProductCatalogIntegrationTest extends AbstractIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProductMongoRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void testCreateProduct() {
        ProductRequest request = new ProductRequest("Lenovo ThinkPad",
                "Notebook professionale",
                new BigDecimal("1299.99"),
                "NOTEBOOK",
                "Lenovo",
                Map.of(
                        "processor", "Intel i7",
                        "ram", 32,
                        "storage", "1TB SSD",
                        "screenSize", 15.6,
                        "touchscreen",
                        false
                )
        );

        ResponseEntity<String> response = restTemplate.postForEntity(
                "/api/catalog/products",
                request,
                String.class
        );

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().contains("Lenovo ThinkPad"));

        ProductDocument product = repository.findAll()
                .stream()
                .filter(p -> p.getName().equals("Lenovo ThinkPad"))
                .findFirst()
                .orElseThrow();

        assertNotNull(product.getId());
        assertEquals("Lenovo ThinkPad", product.getName());
    }
}
