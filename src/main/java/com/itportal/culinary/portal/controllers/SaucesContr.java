package com.itportal.culinary.portal.controllers;


import com.itportal.culinary.portal.entity.Desserts;
import com.itportal.culinary.portal.entity.Sauces;
import com.itportal.culinary.portal.repository.SaucesRepo;
import com.itportal.culinary.portal.service.SaucesService;
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
public class SaucesContr {
    private final SaucesRepo saucesRepo;
    private final SaucesService saucesService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/sauces")
    public String main(Model model) {
        Iterable<Sauces> message = saucesRepo.findAll();

        model.addAttribute("allSauces", message);
        return "Sauces";
    }

    @PostMapping("/sauces")
    public String add(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String ingridients,
            @RequestParam String time,
            @RequestParam String preparation,
            @RequestParam String servings,
            @RequestParam String ennergyValue, Model model,
            @RequestParam(name = "file", required = false) MultipartFile file) throws IOException {
        Sauces sauces = new Sauces();
        sauces.setName(name);
        sauces.setDescription(description);
        sauces.setIngridients(ingridients);
        sauces.setPreparation(preparation);
        sauces.setTime(time);
        sauces.setServings(servings);
        sauces.setEnnergyValue(ennergyValue);
        if (file != null) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            sauces.setImage(resultFilename);
        }
        saucesRepo.save(sauces);
        Iterable<Sauces> message = saucesRepo.findAll();
        model.addAttribute("allSauces", message);
        return "Sauces";
    }

    @GetMapping("/sauces/{id}")
    public String detailsSaucesId(@PathVariable(name = "id") long id,
                                  Model model) {

        model.addAttribute("sauces", saucesService.findById(id));
        return "SaucesDescr";
    }

    @PostMapping("/sauces/{id}/delete")
    public String deleteSaucesId(@PathVariable(name = "id") long id,
                                 Model model) {
        Sauces sauces = saucesRepo.findById(id).orElseThrow();
        saucesRepo.delete(sauces);
        return "redirect:/sauces";
    }
}
