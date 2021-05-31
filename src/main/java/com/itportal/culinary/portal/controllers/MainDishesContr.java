package com.itportal.culinary.portal.controllers;

import com.itportal.culinary.portal.entity.Desserts;
import com.itportal.culinary.portal.entity.MainDishes;
import com.itportal.culinary.portal.repository.MainDishesRepo;
import com.itportal.culinary.portal.service.MainDishesService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class MainDishesContr {
    private final MainDishesRepo mainDishesRepo;
    private final MainDishesService mainDishesService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/main_dishes")
    public String main(Model model) {
        Iterable<MainDishes> message = mainDishesRepo.findAll();

        model.addAttribute("allDishes", message);
        return "MainDishes";
    }

    @PostMapping("/main_dishes")
    public String add(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String ingridients,
            @RequestParam String time,
            @RequestParam String servings,
            @RequestParam String ennergyValue, Model model,
            @RequestParam(name = "file", required = false) MultipartFile file) throws IOException {
        MainDishes mainDishes = new MainDishes();
        mainDishes.setName(name);
        mainDishes.setDescription(description);
        mainDishes.setIngridients(ingridients);
        mainDishes.setTime(time);
        mainDishes.setServings(servings);
        mainDishes.setEnnergyValue(ennergyValue);
        if (file != null) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            mainDishes.setImage(resultFilename);
        }
        mainDishesRepo.save(mainDishes);
        Iterable<MainDishes> message = mainDishesRepo.findAll();
        model.addAttribute("allDishes", message);
        return "MainDishes";
    }

    @GetMapping("/main_dishes/{id}")
    public String detailsMainDishesId(@PathVariable(name = "id") long id,
                                      Model model) {

        model.addAttribute("dishes", mainDishesService.findById(id));
        return "MainDishesDescr";
    }

    @PostMapping("/main_dishes/{id}/delete")
    public String deleteMainDishesId(@PathVariable(name = "id") long id,
                                     Model model) {
        MainDishes mainDishes = mainDishesRepo.findById(id).orElseThrow();
        mainDishesRepo.delete(mainDishes);
        return "redirect:/main_dishes";
    }
}
