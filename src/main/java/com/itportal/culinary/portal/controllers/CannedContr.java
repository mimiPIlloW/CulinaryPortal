package com.itportal.culinary.portal.controllers;

import com.itportal.culinary.portal.entity.Canned;
import com.itportal.culinary.portal.entity.Desserts;
import com.itportal.culinary.portal.repository.CannedRepo;
import com.itportal.culinary.portal.service.CannedService;
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
public class CannedContr {
    private final CannedRepo cannedRepo;
    private final CannedService cannedService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/canned_food")
    public String main(Model model) {
        Iterable<Canned> message = cannedRepo.findAll();

        model.addAttribute("allCanned", message);
        return "CannedFood";
    }

    @PostMapping("/canned_food")
    public String add(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String ingridients,
            @RequestParam String time,
            @RequestParam String servings,
            @RequestParam String ennergyValue,Model model,
            @RequestParam(name = "file", required = false) MultipartFile file) throws IOException {
        Canned canned = new Canned();
        canned.setName(name);
        canned.setDescription(description);
        canned.setIngridients(ingridients);
        canned.setTime(time);
        canned.setServings(servings);
        canned.setEnnergyValue(ennergyValue);
        if(file !=null){
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename =uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath +  "/" + resultFilename));

            canned.setImage(resultFilename);
        }
        cannedRepo.save(canned);
        Iterable<Canned> message = cannedRepo.findAll();
        model.addAttribute("allCanned", message);
        return "CannedFood";
    }

    @GetMapping("/canned_food/{id}")
    public String detailsCannedId(@PathVariable(name = "id") long id,
                                    Model model) {

        model.addAttribute("canned", cannedService.findById(id));
        return "CannedFoodDescr";
    }
}
