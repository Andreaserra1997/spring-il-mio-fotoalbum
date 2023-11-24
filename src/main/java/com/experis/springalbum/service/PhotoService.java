package com.experis.springalbum.service;

import com.experis.springalbum.exceptions.PhotoNotFoundException;
import com.experis.springalbum.model.Photo;
import com.experis.springalbum.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoService {
    @Autowired
    private PhotoRepository photoRepository;

    public List<Photo> getPhotoList(Optional<String> search) {
        if (search.isPresent()) {
            return photoRepository.findByTitleContainingIgnoreCase(search.get());
        } else {
            return photoRepository.findAll();
        }
    }

    public List<Photo> getPhotoList() {
        return photoRepository.findAll();
    }

    public Photo getPhotoById(Integer id) throws PhotoNotFoundException {
        Optional<Photo> result = photoRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new PhotoNotFoundException("La foto con id " + id + " non è stata trovata");
        }
    }

    public Photo createPhoto(Photo photo) {
        photo.setId(null);
        try {
            return photoRepository.save(photo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Photo editPhoto(Photo photo) throws PhotoNotFoundException {
        Photo photoToEdit = getPhotoById(photo.getId());

        photoToEdit.setTitle(photo.getTitle());
        photoToEdit.setDescription(photo.getDescription());
        photoToEdit.setUrl(photo.getUrl());
        photoToEdit.setVisible(photo.getVisible());
        photoToEdit.setCategories(photo.getCategories());

        return photoRepository.save(photoToEdit);
    }

    public void deletePhoto(Integer id) {
        photoRepository.deleteById(id);
    }
}
