package ecommerce_backend.ecommerce_backend.service;

import ecommerce_backend.ecommerce_backend.dto.CartDTO;

public interface CartService {

    CartDTO.Response getCart(String userEmail);

    void clearCart(String userEmail);
}

