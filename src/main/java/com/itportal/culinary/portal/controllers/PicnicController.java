package com.itportal.culinary.portal.controllers;

import com.itportal.culinary.portal.entity.Desserts;
import com.itportal.culinary.portal.entity.Picnic;
import com.itportal.culinary.portal.repository.PicnicRepo;
import com.itportal.culinary.portal.service.PicnicService;
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
public class PicnicController {
    private final PicnicRepo picnicRepo;
    private final PicnicService picnicService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/picnic")
    public String main(Model model) {
        Iterable<Picnic> message = picnicRepo.findAll();

        model.addAttribute("allPicnic", message);
        return "Picnic";
    }

    @PostMapping("/picnic")
    public String add(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String ingridients,
            @RequestParam String time,
            @RequestParam String servings,
            @RequestParam String ennergyValue,Model model,
            @RequestParam(name = "file", required = false) MultipartFile file) throws IOException {
        Picnic picnic = new Picnic();
        picnic.setName(name);
        picnic.setDescription(description);
        picnic.setIngridients(ingridients);
        picnic.setTime(time);
        picnic.setServings(servings);
        picnic.setEnnergyValue(ennergyValue);
        if(file !=null){
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename =uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath +  "/" + resultFilename));

            picnic.setImage(resultFilename);
        }
        picnicRepo.save(picnic);
        Iterable<Picnic> message = picnicRepo.findAll();
        model.addAttribute("allPicnic", message);
        return "Picnic";
    }

    @GetMapping("/picnic/{id}")
    public String detailsPicnicId(@PathVariable(name = "id") long id,
                                    Model model) {

        model.addAttribute("picnic", picnicService.findById(id));
        return "PicnicDescr";
    }
    @PostMapping("/picnic/{id}/delete")
    public String deletePicnicId(@PathVariable(name = "id") long id,
                                  Model model) {
        Picnic picnic = picnicRepo.findById(id).orElseThrow();
        picnicRepo.delete(picnic);
        return "redirect:/picnic";
    }
}
