package com.jlee.bookstore.controllers;

import com.jlee.bookstore.domain.Category;
import com.jlee.bookstore.dto.CategoryDTO;
import com.jlee.bookstore.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(final CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> findById(@PathVariable Integer id) {
        Category obj = categoryService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll() {
        List<Category> categoryList = categoryService.findAll();
        List<CategoryDTO> categoryDTOList = categoryList.stream()
                .map(CategoryDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(categoryDTOList);
    }

    @PostMapping
    public ResponseEntity<Category> create(@Valid @RequestBody Category category) {
        category = categoryService.create(category);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category.getId()).toUri();
        return ResponseEntity.created(uri).body(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> update(@Valid @PathVariable Integer id, @RequestBody CategoryDTO categoryDTO) {
        Category category = categoryService.update(id, categoryDTO);
        return ResponseEntity.ok().body(new CategoryDTO(category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
