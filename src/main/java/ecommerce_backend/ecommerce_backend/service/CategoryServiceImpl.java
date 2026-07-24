package ecommerce_backend.ecommerce_backend.service;

import ecommerce_backend.ecommerce_backend.dto.CategoryDTO;
import ecommerce_backend.ecommerce_backend.model.Category;
import ecommerce_backend.ecommerce_backend.repository.CategoryRepository;
import ecommerce_backend.ecommerce_backend.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDTO.Response create(CategoryDTO.Request request) {

        Category category = Category.builder()
                .name(request.name())
                .build();

        category = categoryRepository.save(category);

        return new CategoryDTO.Response(
                category.getId(),
                category.getName()
        );
    }

    @Override
    public List<CategoryDTO.Response> getAll() {

        return categoryRepository.findAll()
                .stream()
                .map(category -> new CategoryDTO.Response(
                        category.getId(),
                        category.getName()
                ))
                .toList();
    }

    @Override
    public CategoryDTO.Response getById(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Category not found"));

        return new CategoryDTO.Response(
                category.getId(),
                category.getName()
        );
    }

    @Override
    public CategoryDTO.Response update(Long id,
                                       CategoryDTO.Request request) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Category not found"));

        category.setName(request.name());

        category = categoryRepository.save(category);

        return new CategoryDTO.Response(
                category.getId(),
                category.getName()
        );
    }

    @Override
    public void delete(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Category not found"));

        categoryRepository.delete(category);
    }
}