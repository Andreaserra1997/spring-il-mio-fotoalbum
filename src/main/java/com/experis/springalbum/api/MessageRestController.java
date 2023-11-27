package com.experis.springalbum.api;

import com.experis.springalbum.model.Message;
import com.experis.springalbum.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/messages")
@CrossOrigin
public class MessageRestController {
    @Autowired
    MessageService messageService;

    @GetMapping
    public List<Message> index() {
        return messageService.getMessageList();
    }

    @PostMapping
    public Message create(@Valid @RequestBody Message message) {
        return messageService.createMessage(message);
    }
}
