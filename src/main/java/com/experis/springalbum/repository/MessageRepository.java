package com.experis.springalbum.repository;

import com.experis.springalbum.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository  extends JpaRepository<Message, Integer> {
}
