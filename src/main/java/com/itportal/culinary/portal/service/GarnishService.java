package com.itportal.culinary.portal.service;


import com.itportal.culinary.portal.entity.Garnish;
import com.itportal.culinary.portal.repository.GarnishRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GarnishService {
    @Autowired
    GarnishRep garnishRep;

    public List<Garnish> findAll(){
        return garnishRep.findAll();
    }
    public Garnish findByName(String name){
        return garnishRep.findByName(name);
    }

    public Garnish findById(long id){
        Optional<Garnish> garnish=garnishRep.findById(id);
        if(garnish.isPresent()) {
            return garnish.get();
        }
        throw new RuntimeException();
    }
}
