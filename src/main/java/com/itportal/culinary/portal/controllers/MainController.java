package com.itportal.culinary.portal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String HomeGuest(Model model) {
        model.addAttribute("title", "Главная страница");
        return "HomeGuest";
    }

    @GetMapping("/home")
    public String Homes(Model model) {
        model.addAttribute("title", "Главная страница");
        return "HomeUser";
    }

    @GetMapping("/about")
    public String abouts(Model model) {
        model.addAttribute("title", "About us");
        return "aboutUs";
    }

    @GetMapping("/video")
    public String video(Model model) {
        model.addAttribute("title", "About us");
        return "video";
    }

    @GetMapping("/cooking_recipes")
    public String tsded(Model model) {
        model.addAttribute("title", "About us");
        return "CulinarGroup";
    }
}

