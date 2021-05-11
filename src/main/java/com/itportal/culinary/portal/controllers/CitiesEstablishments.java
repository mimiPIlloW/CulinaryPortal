package com.itportal.culinary.portal.controllers;

import com.itportal.culinary.portal.entity.Cities;
import com.itportal.culinary.portal.repository.CitiesEstablishmentsRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class CitiesEstablishments {
    private final CitiesEstablishmentsRepository citiesEstablishmentsRepository;

    public CitiesEstablishments(CitiesEstablishmentsRepository citiesEstablishmentsRepository){
        this.citiesEstablishmentsRepository=citiesEstablishmentsRepository;
    }
    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/CulinaryEstablishments")
    public String main(Model model) {
        Iterable<Cities> messages = citiesEstablishmentsRepository.findAll();

        model.addAttribute("allCities", messages);

        return "Establishments";
    }

    @PostMapping("/CulinaryEstablishments")
    public String add(@RequestParam String name, Model model,
                      @RequestParam(name = "file" , required = false) MultipartFile file) throws IOException {
        Cities group = new Cities();
        group.setName(name);

        if(file !=null){
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename =uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath +  "/" + resultFilename));

            group.setImage(resultFilename);
        }

        citiesEstablishmentsRepository.save(group);

        Iterable<Cities> messages = citiesEstablishmentsRepository.findAll();

        model.addAttribute("allCities", messages);

        return "Establishments";
    }
}
