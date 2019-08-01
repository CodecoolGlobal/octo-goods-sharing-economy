package com.codecool.octogoods.service;

import com.codecool.octogoods.dao.CategoryRepository;
import com.codecool.octogoods.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        categoryRepository.findAll().forEach(categories::add);
        return categories;
    }

    public Category insertCategory(Category category) {
        return categoryRepository.save(category);
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
            categoryToUpdate.setActive(category.isActive());
            return categoryRepository.save(categoryToUpdate);
        } else {
            throw new EntityNotFoundException("Failed to update. Category under given id does not exist.");
        }
    }

    public Category getByName(String name) {
        Optional<Category> category = categoryRepository.getByName(name);
        if (category.isPresent()) {
            return categoryRepository.getByName(name).get();
        } else {
            throw new EntityNotFoundException("Category " + name + " not found");
        }
    }

}