package com.itportal.culinary.portal.service;


import com.itportal.culinary.portal.entity.Snacks;
import com.itportal.culinary.portal.repository.SnacksRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SnacksService {
    @Autowired
    SnacksRep snacksRep;

    public List<Snacks> findAll(){
        return snacksRep.findAll();
    }
    public Snacks findByName(String name){
        return snacksRep.findByName(name);
    }

    public Snacks findById(long id){
        Optional<Snacks> snacks=snacksRep.findById(id);
        if(snacks.isPresent()) {
            return snacks.get();
        }
        throw new RuntimeException();
    }
}
