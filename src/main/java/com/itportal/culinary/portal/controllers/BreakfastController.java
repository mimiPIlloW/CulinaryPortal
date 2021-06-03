package com.itportal.culinary.portal.controllers;

import com.itportal.culinary.portal.entity.Breakfast;
import com.itportal.culinary.portal.entity.Desserts;
import com.itportal.culinary.portal.repository.BreakfastRepo;
import com.itportal.culinary.portal.service.BreakfastService;
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
public class BreakfastController {
    private final BreakfastRepo breakfastRepo;
    private final BreakfastService breakfastService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/breakfast")
    public String main(Model model) {
        Iterable<Breakfast> message = breakfastRepo.findAll();

        model.addAttribute("allBreakfast", message);
        return "Breakfast";
    }

    @PostMapping("/breakfast")
    public String add(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String ingridients,
            @RequestParam String time,
            @RequestParam String preparation,
            @RequestParam String servings,
            @RequestParam String ennergyValue, Model model,
            @RequestParam(name = "file", required = false) MultipartFile file) throws IOException {
        Breakfast breakfast = new Breakfast();
        breakfast.setName(name);
        breakfast.setDescription(description);
        breakfast.setIngridients(ingridients);
        breakfast.setPreparation(preparation);
        breakfast.setTime(time);
        breakfast.setServings(servings);
        breakfast.setEnnergyValue(ennergyValue);
        if (file != null) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            breakfast.setImage(resultFilename);
        }
        breakfastRepo.save(breakfast);
        Iterable<Breakfast> message = breakfastRepo.findAll();
        model.addAttribute("allBreakfast", message);
        return "Breakfast";
    }

    @GetMapping("/breakfast/{id}")
    public String detailsBreakfastId(@PathVariable(name = "id") long id,
                                     Model model) {

        model.addAttribute("breakfast", breakfastService.findById(id));
        return "BreakfastDescr";
    }

    @PostMapping("/breakfast/{id}/delete")
    public String deleteBreakfastId(@PathVariable(name = "id") long id, Model model) {
        Breakfast breakfast = breakfastRepo.findById(id).orElseThrow();
        breakfastRepo.delete(breakfast);
        return "redirect:/breakfast";
    }

}
