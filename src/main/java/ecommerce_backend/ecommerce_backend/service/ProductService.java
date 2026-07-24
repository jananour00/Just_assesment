package ecommerce_backend.ecommerce_backend.service;

import ecommerce_backend.ecommerce_backend.dto.ProductDTO;
import java.util.List;

public interface ProductService {

    ProductDTO.Response create(ProductDTO.Request request);

    List<ProductDTO.Response> getAll();

    ProductDTO.Response getById(Long id);

    List<ProductDTO.Response> getByCategory(Long categoryId);

    ProductDTO.Response update(Long id, ProductDTO.Request request);

    void delete(Long id);
}

