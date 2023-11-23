package com.experis.springalbum.controller;

import com.experis.springalbum.model.User;
import com.experis.springalbum.repository.UserRepository;
import com.experis.springalbum.security.DatabaseUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @GetMapping
        public String index(Authentication authentication, Model model) {
            DatabaseUserDetails principal = (DatabaseUserDetails) authentication.getPrincipal();
            User loggedUser = userRepository.findById(principal.getId()).get();
            model.addAttribute(loggedUser.getFirstName());
            model.addAttribute(loggedUser.getLastName());
            return "users/index";
        }
    }