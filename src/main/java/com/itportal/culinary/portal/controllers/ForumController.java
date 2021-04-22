package com.itportal.culinary.portal.controllers;

import com.itportal.culinary.portal.entity.ForumEntity;
import com.itportal.culinary.portal.repository.ForumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class ForumController {
    @Autowired
    private ForumRepository forumRepository;


    @GetMapping("public/forum")
    public String main(Map<String, Object> model) {
        Iterable<ForumEntity> messages = forumRepository.findAll();

        model.put("messages", messages);

        return "Forum";
    }

    @PostMapping("public/forum")
    public String add(@RequestParam String text,@RequestParam String tag, Map<String, Object> model) {
        ForumEntity message = new ForumEntity(text, tag);

        forumRepository.save(message);

        Iterable<ForumEntity> messages = forumRepository.findAll();

        model.put("messages", messages);

        return "Forum";
    }

    @PostMapping("public/filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<ForumEntity> messages;

        if (filter != null && !filter.isEmpty()) {
            messages = forumRepository.findByTag(filter);
        } else {
            messages = forumRepository.findAll();
        }

        model.put("forum", messages);

        return "Forum";
    }
}