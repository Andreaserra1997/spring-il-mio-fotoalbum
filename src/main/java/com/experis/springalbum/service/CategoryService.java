package com.experis.springalbum.service;

import com.experis.springalbum.exceptions.CategoryNameUniqueException;
import com.experis.springalbum.exceptions.CategoryNotFoundException;
import com.experis.springalbum.model.Category;
import com.experis.springalbum.model.Photo;
import com.experis.springalbum.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Category getCategoryById(Integer id) throws CategoryNotFoundException {
        Optional<Category> result = categoryRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new CategoryNotFoundException("La categoria con id " + id + " non è stata trovata");
        }
    }

    public void deleteCategory(Integer id) {
        Category categoryDelete = getCategoryById(id);
        List<Photo> photos = categoryDelete.getPhotos();
        if (photos.size() > 0) {
            for (Photo photo : photos) {
                List<Category> categoryList = photo.getCategories();
                categoryList.remove(categoryDelete);
            }
            categoryDelete.setPhotos(new ArrayList<>());
        }
        categoryRepository.deleteById(id);
    }
}
