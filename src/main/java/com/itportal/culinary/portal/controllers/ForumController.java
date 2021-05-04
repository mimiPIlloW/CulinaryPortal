package com.itportal.culinary.portal.controllers;

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
    public String main(Map<String, Object> model) {
        Iterable<ForumEntity> messages = forumRepository.findAll();

        model.put("allMessages", messages);

        return "Forum";
    }

    @PostMapping("/forum")
    public String add(@RequestParam String name, Map<String, Object> model) {
        ForumEntity message = new ForumEntity (name);

        forumRepository.save(message);

        Iterable<ForumEntity> messages = forumRepository.findAll();

        model.put("allMessages", messages);

        return "Forum";
    }

}