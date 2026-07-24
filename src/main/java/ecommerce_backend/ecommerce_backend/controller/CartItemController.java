package ecommerce_backend.ecommerce_backend.controller;

import ecommerce_backend.ecommerce_backend.dto.CartDTO;
import ecommerce_backend.ecommerce_backend.service.CartItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart/items")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    @PostMapping
    public ResponseEntity<CartDTO.Response> addItem(
            Authentication authentication,
            @Valid @RequestBody CartDTO.ItemRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cartItemService.addItem(authentication.getName(), request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartDTO.Response> updateItem(
            Authentication authentication,
            @PathVariable Long id,
            @Valid @RequestBody CartDTO.ItemRequest request) {
        return ResponseEntity.ok(cartItemService.updateItem(authentication.getName(), id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeItem(
            Authentication authentication,
            @PathVariable Long id) {
        cartItemService.removeItem(authentication.getName(), id);
        return ResponseEntity.noContent().build();
    }
}

