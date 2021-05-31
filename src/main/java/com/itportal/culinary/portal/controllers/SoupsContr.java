package com.itportal.culinary.portal.controllers;


import com.itportal.culinary.portal.entity.Desserts;
import com.itportal.culinary.portal.entity.Soups;
import com.itportal.culinary.portal.repository.SoupsRep;
import com.itportal.culinary.portal.service.SoupsService;
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
public class SoupsContr {
    private final SoupsRep soupsRep;
    private final SoupsService soupsService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/soups")
    public String main(Model model) {
        Iterable<Soups> message = soupsRep.findAll();

        model.addAttribute("allSoups", message);
        return "Soups";
    }

    @PostMapping("/soups")
    public String add(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String ingridients,
            @RequestParam String time,
            @RequestParam String servings,
            @RequestParam String ennergyValue, Model model,
            @RequestParam(name = "file", required = false) MultipartFile file) throws IOException {
        Soups soups = new Soups();
        soups.setName(name);
        soups.setDescription(description);
        soups.setIngridients(ingridients);
        soups.setTime(time);
        soups.setServings(servings);
        soups.setEnnergyValue(ennergyValue);
        if (file != null) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            soups.setImage(resultFilename);
        }
        soupsRep.save(soups);
        Iterable<Soups> message = soupsRep.findAll();
        model.addAttribute("allSoups", message);
        return "Soups";
    }

    @GetMapping("/soups/{id}")
    public String detailsSoupsId(@PathVariable(name = "id") long id,
                                 Model model) {

        model.addAttribute("soups", soupsService.findById(id));
        return "SoupsDescr";
    }

    @PostMapping("/soups/{id}/delete")
    public String deleteSoupsId(@PathVariable(name = "id") long id,
                                Model model) {
        Soups soups = soupsRep.findById(id).orElseThrow();
        soupsRep.delete(soups);
        return "redirect:/soups";
    }

}
