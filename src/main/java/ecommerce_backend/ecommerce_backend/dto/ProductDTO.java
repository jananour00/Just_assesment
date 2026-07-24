package ecommerce_backend.ecommerce_backend.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class ProductDTO {

    public record Request(
            @NotBlank(message = "Product name is required")
            String name,

            String description,

            @NotNull
            @Positive
            BigDecimal price,

            @NotNull
            @Positive
            Integer stock,

            @NotNull
            Long categoryId

    ) {}

    public record Response(
            Long id,
            String name,
            String description,
            BigDecimal price,
            Integer stock,
            String category

    ) {}

}