package com.itportal.culinary.portal.controllers;

import com.itportal.culinary.portal.entity.Desserts;
import com.itportal.culinary.portal.entity.User;
import com.itportal.culinary.portal.repository.DessertsRepo;
import com.itportal.culinary.portal.service.DessertService;
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
public class DessertsContr {
    private final DessertsRepo dessertsRepo;
    private final DessertService dessertService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/desserts")
    public String main(Model model) {
        List<Desserts> message = dessertsRepo.findAll();
        Collections.sort(message , (left, right) -> (int) (right.getId() - left.getId()));

        model.addAttribute("allDesserts", message);
        return "Desserts";
    }

    @PostMapping("/desserts")
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
        Desserts desserts = new Desserts();
        desserts.setName(name);
        desserts.setDescription(description);
        desserts.setIngridients(ingridients);
        desserts.setPreparation(preparation);
        desserts.setTime(time);
        desserts.setServings(servings);
        desserts.setEnnergyValue(ennergyValue);
        desserts.setAuthor(user);
        if (file != null) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            desserts.setImage(resultFilename);
        }
        dessertsRepo.save(desserts);
        List<Desserts> message = dessertsRepo.findAll();
        Collections.sort(message , (left, right) -> (int) (right.getId() - left.getId()));
        model.addAttribute("allDesserts", message);
        return "Desserts";
    }

    @GetMapping("/desserts/{id}")
    public String detailsDessertsId(@PathVariable(name = "id") long id,
                                    Model model) {

        model.addAttribute("desserts", dessertService.findById(id));
        return "DessertDescr";
    }

    @PostMapping("/desserts/{id}/delete")
    @PreAuthorize("hasAuthority('ADMIN')")

    public String deleteDessertsId(@PathVariable(name = "id") long id,
                                   Model model) {
        Desserts desserts = dessertsRepo.findById(id).orElseThrow();
        dessertsRepo.delete(desserts);
        return "redirect:/desserts";
    }
}
