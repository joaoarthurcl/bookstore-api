package com.jlee.bookstore.services;

import com.jlee.bookstore.domain.Category;
import com.jlee.bookstore.dto.CategoryDTO;
import com.jlee.bookstore.exceptions.ObjectNotFoundException;
import com.jlee.bookstore.repositories.CategoryRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return categoryId.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found. Id: " + id + " and Type: " + Category.class.getName()));
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category create(Category category) {
        category.setId(null);
        return categoryRepository.save(category);
    }

    @Transactional
    public Category update(Integer id, CategoryDTO categoryDTO) {
        var obj = findById(id);
        if (categoryDTO.getName() != null && !categoryDTO.getName().isEmpty()) {
            obj.setName(categoryDTO.getName());
        }
        if (categoryDTO.getDescription() != null && !categoryDTO.getDescription().isEmpty()) {
            obj.setDescription(categoryDTO.getDescription());
        }
        return this.categoryRepository.save(obj);
    }

    @Transactional
    public void delete(Integer id) {
        findById(id);
        try {
            this.categoryRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new com.jlee.bookstore.exceptions.DataIntegrityViolationException("Category cannot be deleted! It has associated books.");
        }
    }
}
