package com.itportal.culinary.portal.service;


import com.itportal.culinary.portal.entity.Drinks;
import com.itportal.culinary.portal.repository.DrinksRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DrinksService {
    @Autowired
    DrinksRep drinksRep;

    public List<Drinks> findAll() {
        return drinksRep.findAll();
    }

    public Drinks findByName(String name) {
        return drinksRep.findByName(name);
    }

    public Drinks findById(long id) {
        Optional<Drinks> drinks = drinksRep.findById(id);
        if (drinks.isPresent()) {
            return drinks.get();
        }
        throw new RuntimeException();
    }
}
