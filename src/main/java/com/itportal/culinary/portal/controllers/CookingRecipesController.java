package com.itportal.culinary.portal.controllers;

import com.itportal.culinary.portal.entity.CookingRecipesEntity;
import com.itportal.culinary.portal.repository.CookingRecipesRepository;
import org.springframework.stereotype.Controller;
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
    public String main(Map<String, Object> model) {
        Iterable<CookingRecipesEntity> messages = cookingRecipesRepository.findAll();

        model.put("messages", messages);

        return "CookingRecipes";
    }

    @PostMapping("/CookingRecipes/add")
    public String add(@RequestParam String name, Map<String, Object> model) {
        CookingRecipesEntity message = new CookingRecipesEntity(name);

        cookingRecipesRepository.save(message);

        Iterable<CookingRecipesEntity> messages = cookingRecipesRepository.findAll();

        model.put("CookingRecipes", messages);

        return "CookingRecipes";
    }

}
