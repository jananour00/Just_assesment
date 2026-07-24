package ecommerce_backend.ecommerce_backend.service;

import ecommerce_backend.ecommerce_backend.dto.CartDTO;

public interface CartItemService {

    CartDTO.Response addItem(String userEmail, CartDTO.ItemRequest request);

    CartDTO.Response updateItem(String userEmail, Long itemId, CartDTO.ItemRequest request);

    void removeItem(String userEmail, Long itemId);
}

