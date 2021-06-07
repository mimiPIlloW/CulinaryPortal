package com.itportal.culinary.portal.controllers;


import com.itportal.culinary.portal.entity.CookingRecipesGroup;
import com.itportal.culinary.portal.entity.Recipes;
import com.itportal.culinary.portal.entity.Salad;
import com.itportal.culinary.portal.entity.User;
import com.itportal.culinary.portal.repository.SaladRep;
import com.itportal.culinary.portal.service.SaladService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
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
import java.util.*;

@Controller
@RequiredArgsConstructor
public class SaladContr {
    private final SaladRep saladRep;
    private final SaladService saladService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/salad")
    public String saladHom(Model model) {
        List<Salad> message = saladRep.findAll();
        Collections.sort(message , (left, right) -> (int) (right.getId() - left.getId()));

        model.addAttribute("allSalad", message);
        return "Salad";
    }

    @PostMapping("/salad")
    public String addRecipes(
            @AuthenticationPrincipal User user,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String ingridients,
            @RequestParam String preparation,
            @RequestParam String time,
            @RequestParam String servings,
            @RequestParam String ennergyValue, Model model,
            @RequestParam(name = "file", required = false) MultipartFile file) throws IOException {
        Salad salad = new Salad();
        salad.setName(name);
        salad.setDescription(description);
        salad.setIngridients(ingridients);
        salad.setPreparation(preparation);
        salad.setTime(time);
        salad.setServings(servings);
        salad.setEnnergyValue(ennergyValue);
        salad.setAuthor(user);
        if (file != null) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFilename));
            salad.setImage(resultFilename);
        }
        saladRep.save(salad);
        List<Salad> message = saladRep.findAll();
        Collections.sort(message , (left, right) -> (int) (right.getId() - left.getId()));

        model.addAttribute("allSalad", message);
        return "Salad";
    }

    @GetMapping("/salad/{id}")
    public String detailsSaladId(@PathVariable(name = "id") long id,
                                 Model model) {

        model.addAttribute("salad", saladService.findById(id));
        return "SaladDescr";
    }

    @PostMapping("/salad/{id}/delete")
    @PreAuthorize("hasAuthority('ADMIN')")

    public String deleteSaladId(@PathVariable(name = "id") long id,
                                Model model) {
        Salad salad = saladRep.findById(id).orElseThrow();
        saladRep.delete(salad);
        return "redirect:/salad";
    }

}
