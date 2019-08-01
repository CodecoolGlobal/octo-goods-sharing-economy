package com.codecool.octogoods.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.server.ResponseStatusException;

import com.codecool.octogoods.model.Category;
import com.codecool.octogoods.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping(params = "active=true")
    public List<Category> getActiveCategories() {
        return categoryService.getAllActiveCategories();
    }

    @GetMapping(params = "active=false")
    public List<Category> getInactiveCategories() {
        return categoryService.getAllInactiveCategories();
    }

    @GetMapping("{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") int id) {
        try {
            return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping(params = {"name"})
    public ResponseEntity<Category> getCategoryByName(@RequestParam(value = "name") String name) {
        name = name.toLowerCase();
        try {
            return new ResponseEntity<>(categoryService.getCategoryByName(name), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Category> addCategory(@Valid @RequestBody Category category) {
        return new ResponseEntity<>(categoryService.insertCategory(category), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Category> updateCategory(@Valid @NotNull @RequestBody Category category) {
        try {
            return new ResponseEntity<>(categoryService.updateCategory(category), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Category> deleteCategoryById(@PathVariable("id") int id) {
        try {
            return new ResponseEntity<>(categoryService.deleteCategoryById(id), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}