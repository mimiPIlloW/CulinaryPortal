package com.itportal.culinary.portal.controllers;

import com.itportal.culinary.portal.entity.CookingRecipesEntity;
import com.itportal.culinary.portal.repository.CookingRecipesRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class CookingRecipesController {

    private final CookingRecipesRepository cookingRecipesRepository;

    public CookingRecipesController(CookingRecipesRepository cookingRecipesRepository) {
        this.cookingRecipesRepository = cookingRecipesRepository;
    }


    @GetMapping("/CookingRecipes")
    public String main(Model model) {
        Iterable<CookingRecipesEntity> messages = cookingRecipesRepository.findAll();

        model.addAttribute("allCookingRecipes", messages);

        return "CookingRecipes";
    }

    @PostMapping("/CookingRecipes")
    public String add(@RequestParam String cookingRecipes, Model model) {
        CookingRecipesEntity message = new CookingRecipesEntity(cookingRecipes);

        cookingRecipesRepository.save(message);

        Iterable<CookingRecipesEntity> messages = cookingRecipesRepository.findAll();

        model.addAttribute("allCookingRecipes", messages);

        return "CookingRecipes";
    }
}
