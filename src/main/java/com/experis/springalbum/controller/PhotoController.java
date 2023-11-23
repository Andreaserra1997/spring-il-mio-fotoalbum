package com.experis.springalbum.controller;

import com.experis.springalbum.model.Photo;
import com.experis.springalbum.repository.PhotoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/photos")
public class PhotoController {
    @Autowired
    private PhotoRepository photoRepository;

    @GetMapping
    public String index(Model model) {
        List<Photo> photoList = photoRepository.findAll();
        model.addAttribute("photoList", photoList);
        return "photos/list";
    }
}
