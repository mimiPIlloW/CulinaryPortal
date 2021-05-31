package com.itportal.culinary.portal.controllers;


import com.itportal.culinary.portal.entity.Desserts;
import com.itportal.culinary.portal.entity.Snacks;
import com.itportal.culinary.portal.repository.SnacksRep;
import com.itportal.culinary.portal.service.SnacksService;
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
public class SnacksController {
    private final SnacksRep snacksRep;
    private final SnacksService snacksService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/snacks")
    public String main(Model model) {
        Iterable<Snacks> message = snacksRep.findAll();

        model.addAttribute("allSnacks", message);
        return "Snacks";
    }

    @PostMapping("/snacks")
    public String add(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String ingridients,
            @RequestParam String time,
            @RequestParam String servings,
            @RequestParam String ennergyValue,Model model,
            @RequestParam(name = "file", required = false) MultipartFile file) throws IOException {
        Snacks snacks = new Snacks();
        snacks.setName(name);
        snacks.setDescription(description);
        snacks.setIngridients(ingridients);
        snacks.setTime(time);
        snacks.setServings(servings);
        snacks.setEnnergyValue(ennergyValue);
        if(file !=null){
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename =uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath +  "/" + resultFilename));

            snacks.setImage(resultFilename);
        }
        snacksRep.save(snacks);
        Iterable<Snacks> message = snacksRep.findAll();
        model.addAttribute("allSnacks", message);
        return "Snacks";
    }

    @GetMapping("/snacks/{id}")
    public String detailsSnacksId(@PathVariable(name = "id") long id,
                                    Model model) {

        model.addAttribute("snacks", snacksService.findById(id));
        return "SnacksDescr";
    }
    @PostMapping("/snacks/{id}/delete")
    public String deleteSnacksId(@PathVariable(name = "id") long id,
                                    Model model) {
        Snacks snacks = snacksRep.findById(id).orElseThrow();
        snacksRep.delete(snacks);
        return "redirect:/snacks";
    }
}
