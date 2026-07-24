package ecommerce_backend.ecommerce_backend.service.impl;

import ecommerce_backend.ecommerce_backend.dto.CartDTO;
import ecommerce_backend.ecommerce_backend.model.Cart;
import ecommerce_backend.ecommerce_backend.model.CartItem;
import ecommerce_backend.ecommerce_backend.model.Product;
import ecommerce_backend.ecommerce_backend.model.User;
import ecommerce_backend.ecommerce_backend.repository.CartItemRepository;
import ecommerce_backend.ecommerce_backend.repository.CartRepository;
import ecommerce_backend.ecommerce_backend.repository.ProductRepository;
import ecommerce_backend.ecommerce_backend.repository.UserRepository;
import ecommerce_backend.ecommerce_backend.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public CartDTO.Response addItem(String userEmail, CartDTO.ItemRequest request) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseGet(() -> {
                    Cart newCart = Cart.builder()
                            .user(user)
                            .totalPrice(BigDecimal.ZERO)
                            .build();
                    return cartRepository.save(newCart);
                });

        Product product = productRepository.findById(request.productId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getStock() < request.quantity()) {
            throw new RuntimeException("Not enough stock available");
        }

        // Check if product already in cart
        CartItem existingItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(request.productId()))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + request.quantity());
            cartItemRepository.save(existingItem);
        } else {
            CartItem newItem = CartItem.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(request.quantity())
                    .build();
            cart.getCartItems().add(newItem);
            cartItemRepository.save(newItem);
        }

        // Recalculate total price
        BigDecimal total = cart.getCartItems().stream()
                .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        cart.setTotalPrice(total);

        cartRepository.save(cart);

        return new CartDTO.Response(
                cart.getId(),
                cart.getCartItems().stream()
                        .map(item -> new CartDTO.ItemResponse(
                                item.getId(),
                                item.getProduct().getName(),
                                item.getQuantity(),
                                item.getProduct().getPrice()
                        ))
                        .toList(),
                cart.getTotalPrice()
        );
    }

    @Override
    @Transactional
    public CartDTO.Response updateItem(String userEmail, Long itemId, CartDTO.ItemRequest request) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        CartItem cartItem = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        if (!cartItem.getCart().getId().equals(cart.getId())) {
            throw new RuntimeException("Cart item does not belong to user's cart");
        }

        Product product = cartItem.getProduct();
        if (product.getStock() < request.quantity()) {
            throw new RuntimeException("Not enough stock available");
        }

        cartItem.setQuantity(request.quantity());
        cartItemRepository.save(cartItem);

        // Recalculate total price
        BigDecimal total = cart.getCartItems().stream()
                .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        cart.setTotalPrice(total);

        cartRepository.save(cart);

        return new CartDTO.Response(
                cart.getId(),
                cart.getCartItems().stream()
                        .map(item -> new CartDTO.ItemResponse(
                                item.getId(),
                                item.getProduct().getName(),
                                item.getQuantity(),
                                item.getProduct().getPrice()
                        ))
                        .toList(),
                cart.getTotalPrice()
        );
    }

    @Override
    @Transactional
    public void removeItem(String userEmail, Long itemId) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        CartItem cartItem = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        if (!cartItem.getCart().getId().equals(cart.getId())) {
            throw new RuntimeException("Cart item does not belong to user's cart");
        }

        cart.getCartItems().remove(cartItem);
        cartItemRepository.delete(cartItem);

        // Recalculate total price
        BigDecimal total = cart.getCartItems().stream()
                .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        cart.setTotalPrice(total);

        cartRepository.save(cart);
    }
}

