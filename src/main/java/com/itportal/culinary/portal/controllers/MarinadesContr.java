package com.itportal.culinary.portal.controllers;

import com.itportal.culinary.portal.entity.Desserts;
import com.itportal.culinary.portal.entity.Marinades;
import com.itportal.culinary.portal.repository.MarinadesRepo;
import com.itportal.culinary.portal.service.MarinadesService;
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
public class MarinadesContr {
    private final MarinadesRepo marinadesRepo;
    private final MarinadesService marinadesService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/marinades")
    public String main(Model model) {
        Iterable<Marinades> message = marinadesRepo.findAll();

        model.addAttribute("allMarinades", message);
        return "Marinades";
    }

    @PostMapping("/marinades")
    public String add(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String ingridients,
            @RequestParam String time,
            @RequestParam String servings,
            @RequestParam String ennergyValue,Model model,
            @RequestParam(name = "file", required = false) MultipartFile file) throws IOException {
        Marinades marinades = new Marinades();
        marinades.setName(name);
        marinades.setDescription(description);
        marinades.setIngridients(ingridients);
        marinades.setTime(time);
        marinades.setServings(servings);
        marinades.setEnnergyValue(ennergyValue);
        if(file !=null){
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename =uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath +  "/" + resultFilename));

            marinades.setImage(resultFilename);
        }
        marinadesRepo.save(marinades);
        Iterable<Marinades> message = marinadesRepo.findAll();
        model.addAttribute("allMarinades", message);
        return "Marinades";
    }

    @GetMapping("/marinades/{id}")
    public String detailsMarinadesId(@PathVariable(name = "id") long id,
                                    Model model) {

        model.addAttribute("marinades", marinadesService.findById(id));
        return "MarinadesDescr";
    }
}
