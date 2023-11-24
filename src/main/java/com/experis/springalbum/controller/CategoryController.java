package com.experis.springalbum.controller;

import com.experis.springalbum.exceptions.CategoryNameUniqueException;
import com.experis.springalbum.exceptions.CategoryNotFoundException;
import com.experis.springalbum.model.Category;
import com.experis.springalbum.model.Photo;
import com.experis.springalbum.repository.PhotoRepository;
import com.experis.springalbum.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    PhotoRepository photoRepository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("categoryList", categoryService.getAll());
        model.addAttribute("categoryObj", new Category());
        return "categories/index";
    }

    @PostMapping
    public String doSave(@Valid @ModelAttribute("categoryObj") Category formCategory, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categoryList", categoryService.getAll());
            return "categories/index";
        }
        try {
            categoryService.save(formCategory);
            return "redirect:/categories";
        } catch (CategoryNameUniqueException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La categoria con il nome " + e.getMessage() + " esiste già");
        }
    }

    @PostMapping("delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        try{
            Category categoryToDelete = categoryService.getCategoryById(id);
            categoryService.deleteCategory(id);
            redirectAttributes.addFlashAttribute("message","La categoria " + categoryToDelete.getName() + " è stata eliminata!"
            );
            return "redirect:/categories";
        }catch (CategoryNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
