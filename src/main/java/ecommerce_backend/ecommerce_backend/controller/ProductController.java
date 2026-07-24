package ecommerce_backend.ecommerce_backend.controller;

import ecommerce_backend.ecommerce_backend.dto.ProductDTO;
import ecommerce_backend.ecommerce_backend.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductDTO.Response> create(
            @Valid @RequestBody ProductDTO.Request request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO.Response>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO.Response> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(productService.getById(id));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductDTO.Response>> getByCategory(
            @PathVariable Long categoryId) {

        return ResponseEntity.ok(productService.getByCategory(categoryId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO.Response> update(
            @PathVariable Long id,
            @Valid @RequestBody ProductDTO.Request request) {

        return ResponseEntity.ok(productService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}