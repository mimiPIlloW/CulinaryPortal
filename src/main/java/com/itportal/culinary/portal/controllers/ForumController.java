package com.itportal.culinary.portal.controllers;

import com.itportal.culinary.portal.entity.CookingRecipesGroup;
import com.itportal.culinary.portal.entity.ForumEntity;
import com.itportal.culinary.portal.entity.User;
import com.itportal.culinary.portal.repository.ForumRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class ForumController {

    private final ForumRepository forumRepository;

    public ForumController(ForumRepository forumRepository) {
        this.forumRepository = forumRepository;
    }


    @GetMapping("/forum")
    public String main(Model model) {
        Iterable<ForumEntity> messages = forumRepository.findAll();

        model.addAttribute("allMessages", messages);

        return "Forum";
    }

    @PostMapping("/forum")
    public String add(@RequestParam String name, String full_text,  Model model) {
        ForumEntity message = new ForumEntity ();
        message.setName(name);
        message.setFull_text(full_text);

        forumRepository.save(message);

        Iterable<ForumEntity> messages = forumRepository.findAll();

        model.addAttribute("allMessages", messages);

        return "Forum";
    }

}