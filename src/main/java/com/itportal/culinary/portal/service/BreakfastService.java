package com.itportal.culinary.portal.service;

import com.itportal.culinary.portal.entity.Breakfast;
import com.itportal.culinary.portal.repository.BreakfastRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BreakfastService {
    @Autowired
    BreakfastRepo breakfastRepo;

    public List<Breakfast> findAll(){
        return breakfastRepo.findAll();
    }
    public Breakfast findByName(String name){
        return breakfastRepo.findByName(name);
    }

    public Breakfast findById(long id){
        Optional<Breakfast> breakfast=breakfastRepo.findById(id);
        if(breakfast.isPresent()) {
            return breakfast.get();
        }
        throw new RuntimeException();
    }
}
