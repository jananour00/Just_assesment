package ecommerce_backend.ecommerce_backend.repository;

import ecommerce_backend.ecommerce_backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {}
