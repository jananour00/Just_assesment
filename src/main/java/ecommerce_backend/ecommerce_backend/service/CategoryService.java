package ecommerce_backend.ecommerce_backend.service;

import ecommerce_backend.ecommerce_backend.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    CategoryDTO.Response create(CategoryDTO.Request request);
    List<CategoryDTO.Response> getAll();
    CategoryDTO.Response getById(Long id);
    CategoryDTO.Response update(Long id, CategoryDTO.Request request);
    void delete(Long id);

}