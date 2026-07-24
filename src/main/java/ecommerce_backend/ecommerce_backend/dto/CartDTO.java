package ecommerce_backend.ecommerce_backend.dto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public class CartDTO {

    public record ItemRequest(
            @NotNull
            Long productId,

            @Positive
            Integer quantity

    ) {}

    public record ItemResponse(
            Long id,
            String productName,
            Integer quantity,
            BigDecimal price

    ) {}

    public record Response(
            Long id,
            List<ItemResponse> items,
            BigDecimal totalPrice

    ) {}

}