package com.itportal.culinary.portal.controllers;

import com.itportal.culinary.portal.entity.ForumEntity;
import com.itportal.culinary.portal.entity.User;
import com.itportal.culinary.portal.exception.CanNotDeleteException;
import com.itportal.culinary.portal.repository.ForumRepository;
import com.itportal.culinary.portal.service.ForumService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ForumController {

    private final ForumRepository forumRepository;
    private final ForumService forumService;


    @GetMapping("/forum")
    public String main(Model model) {
        List<ForumEntity> messages = forumRepository.findAll();
        Collections.sort(messages , (left,right) -> (int) (right.getId() - left.getId()));
        model.addAttribute("allMessages", messages);

        return "Forum";
    }

    @PostMapping("/forum")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String name, String anons, String full_text, Model model) {
        ForumEntity message = new ForumEntity();
        message.setName(name);
        message.setFull_text(full_text);
        message.setAnons(anons);
        message.setAuthor(user);

        forumRepository.save(message);

        List<ForumEntity> messages = forumRepository.findAll();
        Collections.sort(messages , (left,right) -> (int) (right.getId() - left.getId()));
        model.addAttribute("allMessages", messages);

        return "Forum";
    }

    @GetMapping("/forum/{id}")
    public String detailsForumId(@PathVariable(name = "id") long id,
                                 Model model) {

        model.addAttribute("forum", forumService.findById(id));
        return "ForumDescr";
    }

    @PostMapping("/forum/{id}/delete")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteForumId(@PathVariable(name = "id") long id,
                                Model model) {
        ForumEntity forumEntity = forumRepository.findById(id).orElseThrow();
            forumRepository.delete(forumEntity);
        return "redirect:/forum";
    }
}
