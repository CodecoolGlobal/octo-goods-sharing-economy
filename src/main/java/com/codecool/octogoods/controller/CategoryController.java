package com.codecool.octogoods.controller;

import com.codecool.octogoods.model.Category;
import com.codecool.octogoods.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/category")
    public List<Category> getCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping("/category")
    public ResponseEntity<Category> addCategory(@Valid @NonNull @RequestBody Category category) {
        return new ResponseEntity<>(categoryService.insertCategory(category), HttpStatus.CREATED);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") int id) {
        try {
            return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping("/category")
    public ResponseEntity<Category> updateCategory(@Valid @NonNull @RequestBody Category category) {
        try {
            return new ResponseEntity<>(categoryService.updateCategory(category), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}