package com.codecool.octogoods.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

import com.codecool.octogoods.model.Category;
import com.codecool.octogoods.dao.CategoryRepository;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category insertCategory(Category category) {
        category = getCategoryWithExistingName(category);
        category.setActive(true);
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        categoryRepository.findAll().forEach(categories::add);
        return categories;
    }

    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Category under given name does not exist."));
    }

    public Category getCategoryById(int id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category under given id does not exist."));
    }

    private Category getCategoryWithExistingName(Category category) {
        String name = category.getName();
        Optional<Category> optionalCategory = categoryRepository.findByName(name);
        return optionalCategory.orElse(category);
    }

    public Category updateCategory(Category category) {
        Optional<Category> optionalCategory = categoryRepository.findById(category.getId());
        Category categoryToUpdate;
        if (optionalCategory.isPresent()) {
            categoryToUpdate = optionalCategory.get();
            categoryToUpdate.setName(category.getName());
            categoryToUpdate.setActive(categoryToUpdate.isActive());
            return categoryRepository.save(categoryToUpdate);
        } else {
            throw new EntityNotFoundException("Failed to update. Category under given id does not exist.");
        }
    }

    public Category deleteCategoryById(int id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        Category categoryToUpdate;
        if (optionalCategory.isPresent()) {
            categoryToUpdate = optionalCategory.get();
            categoryToUpdate.setActive(false);
            return  categoryRepository.save(categoryToUpdate);
        } else {
            throw new EntityNotFoundException("Failed to delete. Category under given id does not exist.");

        }
    }

    public List<Category> getAllActiveCategories() {
        List<Category> activeCategories = new ArrayList<>();
        categoryRepository.findAllByIsActiveTrue().forEach(activeCategories::add);
        return activeCategories;
    }

    public List<Category> getAllInactiveCategories() {
        List<Category> activeCategories = new ArrayList<>();
        categoryRepository.findAllByIsActiveFalse().forEach(activeCategories::add);
        return activeCategories;
    }
}