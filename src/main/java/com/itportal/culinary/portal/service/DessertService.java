package com.itportal.culinary.portal.service;

import com.itportal.culinary.portal.entity.Desserts;
import com.itportal.culinary.portal.repository.DessertsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DessertService {
    @Autowired
    DessertsRepo dessertsRepo;

    public List<Desserts> findAll() {
        return dessertsRepo.findAll();
    }

    public Desserts findByName(String name) {
        return dessertsRepo.findByName(name);
    }

    public Desserts findById(long id) {
        Optional<Desserts> desserts = dessertsRepo.findById(id);
        if (desserts.isPresent()) {
            return desserts.get();
        }
        throw new RuntimeException();
    }
}
