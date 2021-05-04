package com.itportal.culinary.portal.controllers;

import com.itportal.culinary.portal.entity.CookingRecipesGroup;
import com.itportal.culinary.portal.repository.CookingRecipesRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class CookingRecipesController {

    private final CookingRecipesRepository cookingRecipesRepository;

    public CookingRecipesController(CookingRecipesRepository cookingRecipesRepository) {
        this.cookingRecipesRepository = cookingRecipesRepository;
    }

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/CookingRecipes")
    public String main(Model model) {
        Iterable<CookingRecipesGroup> messages = cookingRecipesRepository.findAll();

        model.addAttribute("allCookingRecipes", messages);

        return "CookingRecipes";
    }

    @PostMapping("/CookingRecipes")
    public String add(@RequestParam String name, Model model,
    @RequestParam(name = "file" , required = false) MultipartFile file) throws IOException {
        CookingRecipesGroup group = new CookingRecipesGroup();
        group.setName(name);

        if(file !=null){
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename =uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath +  "/" + resultFilename));

            group.setImage(resultFilename);
        }
        cookingRecipesRepository.save(group);

        Iterable<CookingRecipesGroup> messages = cookingRecipesRepository.findAll();

        model.addAttribute("allCookingRecipes", messages);

        return "CookingRecipes";
    }
}
