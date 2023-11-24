package com.experis.springalbum.api;

import com.experis.springalbum.exceptions.PhotoNotFoundException;
import com.experis.springalbum.model.Photo;
import com.experis.springalbum.service.PhotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/photos")
@CrossOrigin
public class PhotoRestController {
    @Autowired
    private PhotoService photoService;

    @GetMapping
    public List<Photo> index(@RequestParam Optional<String> search) {
        return photoService.getPhotoList(search);
    }

    @GetMapping("/{id}")
    public Photo details(@PathVariable Integer id) {
        try {
            return photoService.getPhotoById(id);
        } catch (PhotoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    public Photo create(@Valid @RequestParam Photo photo) {
        try {
            return photoService.createPhoto(photo);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Photo update(@PathVariable Integer id, @Valid @RequestBody Photo photo) {
        photo.setId(id);
        try {
            return photoService.editPhoto(photo);
        } catch (PhotoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        try {
            Photo photoToDelete = photoService.getPhotoById(id);
            photoService.deletePhoto(id);
        } catch (PhotoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
