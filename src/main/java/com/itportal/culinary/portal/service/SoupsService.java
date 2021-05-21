package com.itportal.culinary.portal.service;


import com.itportal.culinary.portal.entity.Soups;
import com.itportal.culinary.portal.repository.SoupsRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SoupsService {
    @Autowired
    SoupsRep soupsRep;

    public List<Soups> findAll(){
        return soupsRep.findAll();
    }
    public Soups findByName(String name){
        return soupsRep.findByName(name);
    }

    public Soups findById(long id){
        Optional<Soups> soups=soupsRep.findById(id);
        if(soups.isPresent()) {
            return soups.get();
        }
        throw new RuntimeException();
    }
}
