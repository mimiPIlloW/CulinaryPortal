package com.itportal.culinary.portal.service;

import com.itportal.culinary.portal.entity.Sauces;
import com.itportal.culinary.portal.repository.SaucesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaucesService {
    @Autowired
    SaucesRepo saucesRepo;

    public List<Sauces> findAll() {
        return saucesRepo.findAll();
    }

    public Sauces findByName(String name) {
        return saucesRepo.findByName(name);
    }

    public Sauces findById(long id) {
        Optional<Sauces> sauces = saucesRepo.findById(id);
        if (sauces.isPresent()) {
            return sauces.get();
        }
        throw new RuntimeException();
    }
}
