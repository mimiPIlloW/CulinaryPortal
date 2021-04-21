package com.itportal.culinary.portal.controllers;

import com.itportal.culinary.portal.entity.CookingRecipes;
import com.itportal.culinary.portal.service.CookingRecipesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping("public/CookingRecipes")
public class CookingRecipesController {
    @Autowired
        CookingRecipesService cookingRecipesService;

        @GetMapping()
        public String showAllCookingRecipes(Model model) {
            Iterable<CookingRecipes> allCookingRecipes = cookingRecipesService.findAll();
            model.addAttribute("allCookingRecipes", allCookingRecipes);
            return "CookingRecipes";
        }
    @PostMapping("/add")
    public String addCookingRecipes(@RequestParam(name = "cookingRecipes") String cookingRecipes) {
        if (cookingRecipes.isEmpty())
            return "redirect:public";
//        CookingRecipes currentCookingRecipes = cookingRecipesService.findByName(CookingRecipes);
//        if (currentCookingRecipes != null) {
//            throw new RuntimeException("Такая мышечная группа уже есть");
//        }
        cookingRecipesService.createNewCookingRecipes(cookingRecipes);
        return "redirect:public/cookingRecipes";
    }


}
