package ecommerce_backend.ecommerce_backend.service.impl;

import ecommerce_backend.ecommerce_backend.dto.CartDTO;
import ecommerce_backend.ecommerce_backend.model.Cart;
import ecommerce_backend.ecommerce_backend.model.User;
import ecommerce_backend.ecommerce_backend.repository.CartRepository;
import ecommerce_backend.ecommerce_backend.repository.UserRepository;
import ecommerce_backend.ecommerce_backend.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public CartDTO.Response getCart(String userEmail) {
        Cart cart = getOrCreateCart(userEmail);

        if (cart.getCartItems() == null || cart.getCartItems().isEmpty()) {
            return new CartDTO.Response(cart.getId(), Collections.emptyList(), BigDecimal.ZERO);
        }

        return mapToResponse(cart);
    }

    @Override
    @Transactional
    public void clearCart(String userEmail) {
        Cart cart = getOrCreateCart(userEmail);
        cart.getCartItems().clear();
        cart.setTotalPrice(BigDecimal.ZERO);
        cartRepository.save(cart);
    }

    private Cart getOrCreateCart(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return cartRepository.findByUserId(user.getId())
                .orElseGet(() -> {
                    Cart newCart = Cart.builder()
                            .user(user)
                            .cartItems(Collections.emptyList())
                            .totalPrice(BigDecimal.ZERO)
                            .build();
                    return cartRepository.save(newCart);
                });
    }

    private CartDTO.Response mapToResponse(Cart cart) {
        BigDecimal totalPrice = cart.getTotalPrice() != null ? cart.getTotalPrice() : BigDecimal.ZERO;

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
                totalPrice
        );
    }
}

