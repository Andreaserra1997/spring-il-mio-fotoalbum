package com.experis.springalbum.service;

import com.experis.springalbum.exceptions.CategoryNameUniqueException;
import com.experis.springalbum.model.Category;
import com.experis.springalbum.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findByOrderByName();
    }

    public Category save(Category category) throws CategoryNameUniqueException {
        if (categoryRepository.existsByName(category.getName())) {
            throw new CategoryNameUniqueException(category.getName());
        }
        category.setName(category.getName().toLowerCase());
        return categoryRepository.save(category);
    }
}
