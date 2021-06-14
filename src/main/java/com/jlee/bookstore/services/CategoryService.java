package com.jlee.bookstore.services;

import com.jlee.bookstore.domain.Category;
import com.jlee.bookstore.exceptions.ObjectNotFoundException;
import com.jlee.bookstore.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category findById(Integer id) {
        Optional<Category> categoryId = categoryRepository.findById(id);
        return categoryId.orElseThrow(() -> new ObjectNotFoundException("Object not found. Id = " + id + " and Type: " + Category.class.getName()));
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category create(Category category) {
        category.setId(null);
        return categoryRepository.save(category);
    }
}
