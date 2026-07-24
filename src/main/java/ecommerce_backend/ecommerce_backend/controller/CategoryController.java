package ecommerce_backend.ecommerce_backend.controller;

import ecommerce_backend.ecommerce_backend.dto.CategoryDTO;
import ecommerce_backend.ecommerce_backend.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryDTO.Response> create(
            @Valid @RequestBody CategoryDTO.Request request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO.Response>> getAll() {

        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO.Response> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(categoryService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO.Response> update(
            @PathVariable Long id,
            @Valid @RequestBody CategoryDTO.Request request) {

        return ResponseEntity.ok(
                categoryService.update(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        categoryService.delete(id);

        return ResponseEntity.noContent().build();
    }
}