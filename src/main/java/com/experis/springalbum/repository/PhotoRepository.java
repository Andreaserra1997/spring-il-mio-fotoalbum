package com.experis.springalbum.repository;

import com.experis.springalbum.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {
    List<Photo> findByTitleContainingIgnoreCase(String titleKeyword);
}
