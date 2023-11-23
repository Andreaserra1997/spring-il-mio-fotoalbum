package com.experis.springalbum.controller;

import com.experis.springalbum.model.Photo;
import com.experis.springalbum.repository.PhotoRepository;
import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/photos")
public class PhotoController {
    @Autowired
    private PhotoRepository photoRepository;

    @GetMapping
    public String index(@RequestParam Optional<String> search, Model model) {
        List<Photo> photoList;

        if (search.isPresent()) {
            photoList = photoRepository.findByTitleContainingIgnoreCase(search.get());
        } else {
            photoList = photoRepository.findAll();
        }
        model.addAttribute("photoList", photoList);
        return "photos/list";
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Optional<Photo> result = photoRepository.findById(id);

        if (result.isPresent()) {
            model.addAttribute("photo", result.get());
            return "photos/show";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La foto con " + id + " non è stata trovata!");
        }
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("photo", new Photo());
        return "photos/form";
    }

    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute("photo") Photo formPhoto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "photos/form";
        }
        Photo savedPhoto = null;
        try {
            savedPhoto = photoRepository.save(formPhoto);
        } catch (RuntimeException e) {
            bindingResult.addError(new FieldError("photo", "id", formPhoto.getId(), false, null, null, "ID deve essere univoco!"));
            return "photos/form";
        }
        return "redirect:/photos/show/" + savedPhoto.getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Optional<Photo> result = photoRepository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("photo", result.get());
            return "/photos/form";
        } else  {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La foto con id " + id + " non è stata trovata");
        }
    }

    @PostMapping("/edit/{id}")
    public String doEdit(@PathVariable Integer id, @Valid @ModelAttribute("photo") Photo formPhoto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/photos/form";
        }
        Photo photoToEdit = photoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        photoToEdit.setTitle(formPhoto.getTitle());
        photoToEdit.setDescription(formPhoto.getDescription());
        photoToEdit.setUrl(formPhoto.getUrl());
        photoToEdit.setVisible(formPhoto.getVisible());
        Photo savedPhoto = photoRepository.save(photoToEdit);
        return "redirect:/photos/show/" +  savedPhoto.getId();
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Photo photoToDelete = photoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        photoRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "La foto " + photoToDelete.getTitle() + " è stata eliminata");
        return "redirect:/photos";
    }
}
