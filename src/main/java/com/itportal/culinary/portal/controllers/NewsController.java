//package com.itportal.culinary.portal.controllers;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.*;
//import com.itportal.culinary.portal.entity.newsEntity;
//import com.itportal.culinary.portal.repository.NewsRepository;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.Map;
//
//@Controller
//public class NewsController {
//    private final NewsController newsController;
//
//    public NewsController(NewsController newsController) {
//
//        this.newsController = newsController;
//    }
//
//    @GetMapping("/NewsNews")
//    public String main(Model model) {
//        Iterable<newsEntity> messages = NewsRepository.findAll();
//
//        model.addAttribute("allNewsNews", messages);
//
//        return "news";
//    }
//
//    @PostMapping("/NewsNews")
//    public String add(@RequestParam String name, Map<String, Object> model) {
//        newsEntity message = new newsEntity(name);
//
//       NewsRepository.save(message);
//
//        Iterable<newsEntity> messages = NewsRepository.findAll();
//
//        model.put("allNewsNews", messages);
//
//        return "news";
//    }
//}
