package ecommerce_backend.ecommerce_backend.repository;

import ecommerce_backend.ecommerce_backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategoryId(Long categoryId);

}