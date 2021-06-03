package com.itportal.culinary.portal.controllers;

import com.itportal.culinary.portal.entity.Children;
import com.itportal.culinary.portal.entity.Desserts;
import com.itportal.culinary.portal.repository.ChildrenRepo;
import com.itportal.culinary.portal.service.ChildrenService;
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
public class ChildrenContr {
    private final ChildrenRepo childrenRepo;
    private final ChildrenService childrenService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/for_children")
    public String main(Model model) {
        Iterable<Children> message = childrenRepo.findAll();

        model.addAttribute("allChildren", message);
        return "ForChildren";
    }

    @PostMapping("/for_children")
    public String add(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String ingridients,
            @RequestParam String time,
            @RequestParam String preparation,
            @RequestParam String servings,
            @RequestParam String ennergyValue, Model model,
            @RequestParam(name = "file", required = false) MultipartFile file) throws IOException {
        Children children = new Children();
        children.setName(name);
        children.setDescription(description);
        children.setIngridients(ingridients);
        children.setPreparation(preparation);
        children.setTime(time);
        children.setServings(servings);
        children.setEnnergyValue(ennergyValue);
        if (file != null) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            children.setImage(resultFilename);
        }
        childrenRepo.save(children);
        Iterable<Children> message = childrenRepo.findAll();
        model.addAttribute("allChildren", message);
        return "ForChildren";
    }

    @GetMapping("/for_children/{id}")
    public String detailsChildrenId(@PathVariable(name = "id") long id,
                                    Model model) {

        model.addAttribute("children", childrenService.findById(id));
        return "ForChildrenDescr";
    }

    @PostMapping("/for_children/{id}/delete")
    public String deleteChildrenId(@PathVariable(name = "id") long id,
                                   Model model) {
        Children children = childrenRepo.findById(id).orElseThrow();
        childrenRepo.delete(children);
        return "redirect:/for_children";
    }
}
