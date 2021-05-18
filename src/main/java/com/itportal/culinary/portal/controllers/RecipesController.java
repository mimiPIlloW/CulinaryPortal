package com.itportal.culinary.portal.controllers;

import com.itportal.culinary.portal.entity.Recipes;
import com.itportal.culinary.portal.repository.RecipesRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class RecipesController {
    private final RecipesRepository recipesRepository;

    public RecipesController(RecipesRepository recipesRepository) {
        this.recipesRepository = recipesRepository;
    }

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/recipes")
    public String main(Model model) {
        Iterable<Recipes> message = recipesRepository.findAll();

        model.addAttribute("allRecipes", message);
        return "Recipes";
    }

    @GetMapping("/recipes/{id}")
    public String details(@PathVariable(name = "id") long id, Model model) {
        Iterable<Recipes> message = recipesRepository.findAll();
        model.addAttribute("allRecipes", message);
        return "redirect:/Recipes/";
    }

    @PostMapping("/recipes/{id}")
    public String add(@PathVariable(name = "id") Long id,
                      @RequestParam String name,
                      @RequestParam String description,
                      @RequestParam String ingridients,
                      @RequestParam String time,
                      @RequestParam String servings,
                      @RequestParam String ennergyValue,Model model,
                      @RequestParam(name = "file", required = false) MultipartFile file) throws IOException {
        Recipes group = new Recipes();
        group.setName(name);
        group.setName(description);
        group.setName(ingridients);
        group.setName(time);
        group.setName(servings);
        group.setName(ennergyValue);

        if (file != null) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            group.setImage(resultFilename);
        }
        recipesRepository.save(group);
        Iterable<Recipes> message = recipesRepository.findAll();
        model.addAttribute("allRecipes", message);
        return "redirect:/Recipes/";
    }
}
