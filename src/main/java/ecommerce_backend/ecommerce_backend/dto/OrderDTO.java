package ecommerce_backend.ecommerce_backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderDTO {

    public record ItemResponse(
            Long id,
            String productName,
            Integer quantity,
            BigDecimal price
    ) {}

    public record Response(
            Long id,
            List<ItemResponse> items,
            BigDecimal totalPrice,
            LocalDateTime orderDate
    ) {}
}

