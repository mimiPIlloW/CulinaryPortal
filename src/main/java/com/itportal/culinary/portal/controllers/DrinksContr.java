package com.itportal.culinary.portal.controllers;

import com.itportal.culinary.portal.entity.Drinks;
import com.itportal.culinary.portal.entity.User;
import com.itportal.culinary.portal.repository.DrinksRep;
import com.itportal.culinary.portal.service.DrinksService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
public class DrinksContr {
    private final DrinksRep drinksRep;
    private final DrinksService drinksService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/drinks")
    public String main(Model model) {
        Iterable<Drinks> message = drinksRep.findAll();

        model.addAttribute("allDrinks", message);
        return "Drinks";
    }

    @PostMapping("/drinks")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String ingridients,
            @RequestParam String time,
            @RequestParam String preparation,
            @RequestParam String servings,
            @RequestParam String ennergyValue, Model model,
            @RequestParam(name = "file", required = false) MultipartFile file) throws IOException {
        Drinks drinks = new Drinks();
        drinks.setName(name);
        drinks.setDescription(description);
        drinks.setIngridients(ingridients);
        drinks.setPreparation(preparation);
        drinks.setTime(time);
        drinks.setServings(servings);
        drinks.setEnnergyValue(ennergyValue);
        drinks.setAuthor(user);
        if (file != null) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            drinks.setImage(resultFilename);
        }
        drinksRep.save(drinks);
        Iterable<Drinks> message = drinksRep.findAll();
        model.addAttribute("allDrinks", message);
        return "Drinks";
    }

    @GetMapping("/drinks/{id}")
    public String detailsDrinksId(@PathVariable(name = "id") long id,
                                  Model model) {

        model.addAttribute("drinks", drinksService.findById(id));
        return "DrinksDescr";
    }

    @PostMapping("/drinks/{id}/delete")
    public String deleteDrinksId(@PathVariable(name = "id") long id,
                                 Model model) {
        Drinks drinks = drinksRep.findById(id).orElseThrow();
        drinksRep.delete(drinks);
        return "redirect:/drinks";
    }
}
