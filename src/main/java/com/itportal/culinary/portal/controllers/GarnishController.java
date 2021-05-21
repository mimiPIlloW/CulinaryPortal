package com.itportal.culinary.portal.controllers;


import com.itportal.culinary.portal.entity.Desserts;
import com.itportal.culinary.portal.entity.Garnish;
import com.itportal.culinary.portal.repository.GarnishRep;
import com.itportal.culinary.portal.service.GarnishService;
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
public class GarnishController {
    private final GarnishRep garnishRep;
    private final GarnishService garnishService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/side_dishes")
    public String main(Model model) {
        Iterable<Garnish> message = garnishRep.findAll();

        model.addAttribute("allGarnish", message);
        return "Garnish";
    }

    @PostMapping("/side_dishes")
    public String add(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String ingridients,
            @RequestParam String time,
            @RequestParam String servings,
            @RequestParam String ennergyValue,Model model,
            @RequestParam(name = "file", required = false) MultipartFile file) throws IOException {
        Garnish garnish = new Garnish();
        garnish.setName(name);
        garnish.setDescription(description);
        garnish.setIngridients(ingridients);
        garnish.setTime(time);
        garnish.setServings(servings);
        garnish.setEnnergyValue(ennergyValue);
        if(file !=null){
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename =uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath +  "/" + resultFilename));

            garnish.setImage(resultFilename);
        }
        garnishRep.save(garnish);
        Iterable<Garnish> message = garnishRep.findAll();
        model.addAttribute("allGarnish", message);
        return "Garnish";
    }

    @GetMapping("/side_dishes/{id}")
    public String detailsSideDishesId(@PathVariable(name = "id") long id,
                                    Model model) {

        model.addAttribute("garnish", garnishService.findById(id));
        return "GarnishDescr";
    }

}
