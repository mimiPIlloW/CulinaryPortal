package com.itportal.culinary.portal.controllers;

import com.itportal.culinary.portal.entity.Cities;
import com.itportal.culinary.portal.entity.Establishments;
import com.itportal.culinary.portal.repository.EstablishmentsRepository;
import com.itportal.culinary.portal.service.EstablishmentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class EstablishmentsController {
    private final EstablishmentsRepository establishmentsRepository;
    private final EstablishmentsService establishmentsService;


    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/culinary_establishments")
    public String main(Model model) {
        List<Establishments> messages = establishmentsRepository.findAll();
        Collections.sort(messages , (left, right) -> (int) (right.getId() - left.getId()));
        model.addAttribute("allEstablishments", messages);

        return "Establishments";
    }

    @PostMapping("/culinary_establishments")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String add(@RequestParam String name,
                      @RequestParam String description,
                      @RequestParam String address,
                      @RequestParam String opening_hours,
                      @RequestParam String telephone, Model model,
                      @RequestParam(name = "file", required = false) MultipartFile file) throws IOException {
        Establishments establishments = new Establishments();
        establishments.setName(name);
        establishments.setDescription(description);
        establishments.setAddress(address);
        establishments.setOpening_hours(opening_hours);
        establishments.setTelephone(telephone);

        if (file != null) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            establishments.setImage(resultFilename);
        }

        establishmentsRepository.save(establishments);

        List<Establishments> messages = establishmentsRepository.findAll();
        Collections.sort(messages , (left, right) -> (int) (right.getId() - left.getId()));
        model.addAttribute("allEstablishments", messages);

        return "Establishments";
    }

    @GetMapping("/culinary_establishments/{id}")
    public String detailsEstablishmentsId(@PathVariable(name = "id") long id,
                                          Model model) {

        model.addAttribute("establishments", establishmentsService.findById(id));
        return "EstablishmentsDescr";
    }

    @PostMapping("/culinary_establishments/{id}/delete")
    @PreAuthorize("hasAuthority('ADMIN')")

    public String deleteEstablishmentsId(@PathVariable(name = "id") long id,
                                         Model model) {
        Establishments establishments = establishmentsRepository.findById(id).orElseThrow();
        establishmentsRepository.delete(establishments);
        return "redirect:/culinary_establishments";
    }
}
