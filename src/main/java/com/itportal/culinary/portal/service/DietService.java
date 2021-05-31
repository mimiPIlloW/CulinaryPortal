package com.itportal.culinary.portal.service;

import com.itportal.culinary.portal.entity.Desserts;
import com.itportal.culinary.portal.entity.Diet;
import com.itportal.culinary.portal.repository.DietRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DietService {
    @Autowired
    DietRepo dietRepo;

    public List<Diet> findAll() {
        return dietRepo.findAll();
    }

    public Diet findByName(String name) {
        return dietRepo.findByName(name);
    }

    public Diet findById(long id) {
        Optional<Diet> diet = dietRepo.findById(id);
        if (diet.isPresent()) {
            return diet.get();
        }
        throw new RuntimeException();
    }
}
