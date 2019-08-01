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

    //TODO: getAllActiveCategories, getAllNotActiveCategories
}