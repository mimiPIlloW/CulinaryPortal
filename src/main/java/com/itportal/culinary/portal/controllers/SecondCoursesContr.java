package com.itportal.culinary.portal.controllers;


import com.itportal.culinary.portal.entity.Desserts;
import com.itportal.culinary.portal.entity.SecondCourses;
import com.itportal.culinary.portal.entity.User;
import com.itportal.culinary.portal.repository.SecondCoursesRep;
import com.itportal.culinary.portal.service.SecondCoursesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class SecondCoursesContr {
    public final SecondCoursesRep secondCoursesRep;
    public final SecondCoursesService secondCoursesService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/second_courses")
    public String main(Model model) {
        Iterable<SecondCourses> message = secondCoursesRep.findAll();

        model.addAttribute("allSecondCourses", message);
        return "SecondCourses";
    }

    @PostMapping("/second_courses")
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
        SecondCourses secondCourses = new SecondCourses();
        secondCourses.setName(name);
        secondCourses.setDescription(description);
        secondCourses.setIngridients(ingridients);
        secondCourses.setPreparation(preparation);
        secondCourses.setTime(time);
        secondCourses.setServings(servings);
        secondCourses.setEnnergyValue(ennergyValue);
        secondCourses.setAuthor(user);
        if (file != null) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            secondCourses.setImage(resultFilename);
        }
        secondCoursesRep.save(secondCourses);
        Iterable<SecondCourses> message = secondCoursesRep.findAll();
        model.addAttribute("allSecondCourses", message);
        return "SecondCourses";
    }

    @GetMapping("/second_courses/{id}")
    public String detailsSecondCoursessId(@PathVariable(name = "id") long id,
                                          Model model) {

        model.addAttribute("secondCourses", secondCoursesService.findById(id));
        return "SecondCoursesDescr";
    }

    @PostMapping("/second_courses/{id}/delete")
    public String deleteSecondCoursessId(@PathVariable(name = "id") long id,
                                         Model model) {
        SecondCourses secondCourses = secondCoursesRep.findById(id).orElseThrow();
        secondCoursesRep.delete(secondCourses);
        return "redirect:/second_courses";
    }


}
