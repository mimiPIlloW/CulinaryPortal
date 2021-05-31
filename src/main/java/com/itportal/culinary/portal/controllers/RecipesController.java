//package com.itportal.culinary.portal.controllers;
//
//import com.itportal.culinary.portal.entity.Recipes;
//import com.itportal.culinary.portal.repository.RecipesRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.UUID;
//
//@Controller
//@RequiredArgsConstructor
//public class RecipesController {
//    private final RecipesRepository recipesRepository;
//
//    @Value("${upload.path}")
//    private String uploadPath;
//
//    @GetMapping("/recipes")
//    public String main(Model model) {
//        Iterable<Recipes> message = recipesRepository.findAll();
//
//        model.addAttribute("allRecipes", message);
//        return "Recipes";
//    }
//
//    @PostMapping("/recipes/add")
//    public String add(
////            @PathVariable(name = "id") Long id,
//                      @RequestParam String name,
//                      @RequestParam(name = "groupId") Long groupId,
//                      @RequestParam String description,
//                      @RequestParam String ingridients,
//                      @RequestParam String time,
//                      @RequestParam String servings,
//                      @RequestParam String ennergyValue,Model model,
//                      @RequestParam(name = "file", required = false) MultipartFile file) throws IOException {
//        Recipes recipes = new Recipes();
//        recipes.setName(name);
//        recipes.setDescription(description);
//        recipes.setIngridients(ingridients);
//        recipes.setTime(time);
//        recipes.setServings(servings);
//        recipes.setEnnergyValue(ennergyValue);
//        if (file != null) {
//            File uploadDir = new File(uploadPath);
//            if (!uploadDir.exists()) {
//                uploadDir.mkdir();
//            }
//            String uuidFile = UUID.randomUUID().toString();
//            String resultFilename = uuidFile + "." + file.getOriginalFilename();
//            file.transferTo(new File(uploadPath + "/" + resultFilename));
//            recipes.setImage(resultFilename);
//        }
//        recipesRepository.save(recipes);
//        Iterable<Recipes> message = recipesRepository.findAll();
//        model.addAttribute("allRecipes", message);
//
//        return "redirect:/CookingRecipes/" + groupId;
//    }
//}
//
