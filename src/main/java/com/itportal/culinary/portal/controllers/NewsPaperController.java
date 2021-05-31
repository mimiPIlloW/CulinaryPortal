package com.itportal.culinary.portal.controllers;

import com.itportal.culinary.portal.entity.NewsEntity;
import com.itportal.culinary.portal.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
public class NewsPaperController {
    private final NewsRepository newsRepository;
    public NewsPaperController (NewsRepository newsRepository){
        this.newsRepository = newsRepository;
    }
    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/newspaper")
    public String main(Model model){
        Iterable <NewsEntity> message = newsRepository.findAll();
        model.addAttribute("allNewsPaper", message);
        return "news";
    }

    @PostMapping("/newspaper")
    public String add (@RequestParam String name, Model model,
                       @RequestParam (name = "file", required = false) MultipartFile file) throws IOException{

        NewsEntity group = new NewsEntity();
        group.setName(name);

        if(file !=null){
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename =uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath +  "/" + resultFilename));

            group.setImage(resultFilename); }

        newsRepository.save(group);
        Iterable <NewsEntity> message = newsRepository.findAll();
        model.addAttribute("allNewsPaper", message);
        return "news";
    }
    @PostMapping("/news/{id}/delete")
    public String deleteNewsId(@PathVariable(name = "id") long id,
                                     Model model) {
        NewsEntity newsEntity = newsRepository.findById(id).orElseThrow();
        newsRepository.delete(newsEntity);
        return "redirect:/newspaper";
}
    }
