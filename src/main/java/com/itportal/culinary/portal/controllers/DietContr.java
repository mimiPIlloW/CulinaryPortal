package com.itportal.culinary.portal.controllers;

import com.itportal.culinary.portal.entity.Desserts;
import com.itportal.culinary.portal.entity.Diet;
import com.itportal.culinary.portal.entity.User;
import com.itportal.culinary.portal.repository.DietRepo;
import com.itportal.culinary.portal.service.DietService;
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
public class DietContr {
    private final DietRepo dietRepo;
    private final DietService dietService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/diet_food")
    public String main(Model model) {
        Iterable<Diet> message = dietRepo.findAll();

        model.addAttribute("allDiet", message);
        return "Diet";
    }

    @PostMapping("/diet_food")
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
        Diet diet = new Diet();
        diet.setName(name);
        diet.setDescription(description);
        diet.setIngridients(ingridients);
        diet.setPreparation(preparation);
        diet.setTime(time);
        diet.setServings(servings);
        diet.setEnnergyValue(ennergyValue);
        diet.setAuthor(user);
        if (file != null) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            diet.setImage(resultFilename);
        }
        dietRepo.save(diet);
        Iterable<Diet> message = dietRepo.findAll();
        model.addAttribute("allDiet", message);
        return "Diet";
    }

    @GetMapping("/diet_food/{id}")
    public String detailsDietId(@PathVariable(name = "id") long id,
                                Model model) {

        model.addAttribute("diet", dietService.findById(id));
        return "DietDescr";
    }

    @PostMapping("/diet_food/{id}/delete")
    public String deleteDietId(@PathVariable(name = "id") long id,
                               Model model) {
        Diet diet = dietRepo.findById(id).orElseThrow();
        dietRepo.delete(diet);
        return "redirect:/diet_food";
    }
}
