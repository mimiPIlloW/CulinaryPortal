package com.itportal.culinary.portal.service;

import com.itportal.culinary.portal.entity.MainDishes;
import com.itportal.culinary.portal.repository.MainDishesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MainDishesService {
    @Autowired
    MainDishesRepo mainDishesRepo;

    public List<MainDishes> findAll() {
        return mainDishesRepo.findAll();
    }

    public MainDishes findByName(String name) {
        return mainDishesRepo.findByName(name);
    }

    public MainDishes findById(long id) {
        Optional<MainDishes> dishes = mainDishesRepo.findById(id);
        if (dishes.isPresent()) {
            return dishes.get();
        }
        throw new RuntimeException();
    }
}

