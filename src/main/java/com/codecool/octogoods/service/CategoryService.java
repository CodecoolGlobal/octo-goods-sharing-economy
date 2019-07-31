package com.codecool.octogoods.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codecool.octogoods.model.Category;
import com.codecool.octogoods.dao.CategoryRepository;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        Iterable<Category> categoryIterable = categoryRepository.findAll();
        List<Category> categories = new ArrayList<>();
        categoryIterable.forEach(categories::add);
        return categories;
    }
}
