package ecommerce_backend.ecommerce_backend.service.impl;

import ecommerce_backend.ecommerce_backend.dto.ProductDTO;
import ecommerce_backend.ecommerce_backend.model.Category;
import ecommerce_backend.ecommerce_backend.model.Product;
import ecommerce_backend.ecommerce_backend.repository.CategoryRepository;
import ecommerce_backend.ecommerce_backend.repository.ProductRepository;
import ecommerce_backend.ecommerce_backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProductDTO.Response create(ProductDTO.Request request) {

        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = Product.builder()
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .stock(request.stock())
                .category(category)
                .build();

        product = productRepository.save(product);

        return mapToResponse(product);
    }

    @Override
    public List<ProductDTO.Response> getAll() {

        return productRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ProductDTO.Response getById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return mapToResponse(product);
    }

    @Override
    public List<ProductDTO.Response> getByCategory(Long categoryId) {

        return productRepository.findByCategoryId(categoryId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ProductDTO.Response update(Long id, ProductDTO.Request request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setStock(request.stock());
        product.setCategory(category);

        product = productRepository.save(product);

        return mapToResponse(product);
    }

    @Override
    public void delete(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        productRepository.delete(product);
    }

    private ProductDTO.Response mapToResponse(Product product) {

        return new ProductDTO.Response(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getCategory().getName()
        );
    }

}

