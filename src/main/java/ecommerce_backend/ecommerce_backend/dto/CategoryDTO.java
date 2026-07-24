package ecommerce_backend.ecommerce_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryDTO {

    public record Request(
            @NotBlank(message = "Category name is required")
            @Size(max = 100, message = "Category name must not exceed 100 characters")
            String name

    ) {}

    public record Response(
            Long id,
            String name

    ) {}

}