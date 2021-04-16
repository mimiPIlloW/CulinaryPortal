package com.itportal.culinary.portal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping("public/signin")
    public String homeP(Model model) {
        model.addAttribute("title", "Главная страница");
        return "HomeUser";
    }
    @GetMapping("public/page/new")
    public String homePs(Model model) {
        model.addAttribute("title", "Главная страница");
        return "HomeGuest";
    }
}