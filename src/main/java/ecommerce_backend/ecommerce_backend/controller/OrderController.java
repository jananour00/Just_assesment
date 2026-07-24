package ecommerce_backend.ecommerce_backend.controller;

import ecommerce_backend.ecommerce_backend.dto.OrderDTO;
import ecommerce_backend.ecommerce_backend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/checkout")
    public ResponseEntity<OrderDTO.Response> checkout(Authentication authentication) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.checkout(authentication.getName()));
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO.Response>> getUserOrders(Authentication authentication) {
        return ResponseEntity.ok(orderService.getUserOrders(authentication.getName()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO.Response> getOrderById(
            Authentication authentication,
            @PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id, authentication.getName()));
    }
}

