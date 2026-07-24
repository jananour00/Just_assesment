package ecommerce_backend.ecommerce_backend.service;

import ecommerce_backend.ecommerce_backend.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    OrderDTO.Response checkout(String userEmail);

    List<OrderDTO.Response> getUserOrders(String userEmail);

    OrderDTO.Response getOrderById(Long orderId, String userEmail);
}

