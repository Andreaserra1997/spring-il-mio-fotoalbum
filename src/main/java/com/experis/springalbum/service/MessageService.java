package com.experis.springalbum.service;

import com.experis.springalbum.model.Message;
import com.experis.springalbum.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    MessageRepository messageRepository;

    public List<Message> getMessageList() {
        return messageRepository.findAll();
    }

    public Message createMessage(Message message) {
        message.setId(null);
        message.setCreatedAt(LocalDateTime.now());
        return messageRepository.save(message);
    }
}
