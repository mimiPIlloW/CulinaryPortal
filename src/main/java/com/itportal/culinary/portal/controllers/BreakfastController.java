package com.itportal.culinary.portal.controllers;

import com.itportal.culinary.portal.entity.Breakfast;
import com.itportal.culinary.portal.entity.Desserts;
import com.itportal.culinary.portal.entity.User;
import com.itportal.culinary.portal.repository.BreakfastRepo;
import com.itportal.culinary.portal.service.BreakfastService;
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
import java.util.Collections;
import java.util.List;
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
        List<Breakfast> message = breakfastRepo.findAll();
        Collections.sort(message , (left, right) -> (int) (right.getId() - left.getId()));

        model.addAttribute("allBreakfast", message);
        return "Breakfast";
    }

    @PostMapping("/breakfast")
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
        Breakfast breakfast = new Breakfast();
        breakfast.setName(name);
        breakfast.setDescription(description);
        breakfast.setIngridients(ingridients);
        breakfast.setPreparation(preparation);
        breakfast.setTime(time);
        breakfast.setServings(servings);
        breakfast.setEnnergyValue(ennergyValue);
        breakfast.setAuthor(user);
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
        List<Breakfast> message = breakfastRepo.findAll();
        Collections.sort(message , (left,right) -> (int) (right.getId() - left.getId()));
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
    @PreAuthorize("hasAuthority('ADMIN')")

    public String deleteBreakfastId(@PathVariable(name = "id") long id, Model model) {
        Breakfast breakfast = breakfastRepo.findById(id).orElseThrow();
        breakfastRepo.delete(breakfast);
        return "redirect:/breakfast";
    }

}
