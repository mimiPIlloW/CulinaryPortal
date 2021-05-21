package com.itportal.culinary.portal.service;

import com.itportal.culinary.portal.entity.Desserts;
import com.itportal.culinary.portal.entity.Marinades;
import com.itportal.culinary.portal.repository.MarinadesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarinadesService {
    @Autowired
    MarinadesRepo marinadesRepo;

    public List<Marinades> findAll(){
        return marinadesRepo.findAll();
    }
    public Marinades findByName(String name){
        return marinadesRepo.findByName(name);
    }

    public Marinades findById(long id){
        Optional<Marinades> marinades=marinadesRepo.findById(id);
        if(marinades.isPresent()) {
            return marinades.get();
        }
        throw new RuntimeException();
    }
}
