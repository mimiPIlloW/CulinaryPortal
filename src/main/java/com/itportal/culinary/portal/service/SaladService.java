package com.itportal.culinary.portal.service;

import com.itportal.culinary.portal.entity.Salad;
import com.itportal.culinary.portal.repository.SaladRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaladService {
    @Autowired
    SaladRep saladRep;

    public List<Salad> findAll() {
        return saladRep.findAll();
    }

    public Salad findByName(String name) {
        return saladRep.findByName(name);
    }

    public Salad findById(long id) {
        Optional<Salad> salad = saladRep.findById(id);
        if (salad.isPresent()) {
            return salad.get();
        }
        throw new RuntimeException();
    }

    public boolean deleteRecipesId(long id) {
        Optional<Salad> saladOptional = saladRep.findById(id);
        saladRep.delete(saladOptional.get());
        return true;
    }
}
