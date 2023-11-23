package com.experis.springalbum.repository;

import com.experis.springalbum.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
