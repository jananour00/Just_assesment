package ecommerce_backend.ecommerce_backend.repository;

import ecommerce_backend.ecommerce_backend.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}

