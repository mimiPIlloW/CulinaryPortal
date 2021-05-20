package com.itportal.culinary.portal.service;

import com.itportal.culinary.portal.entity.Desserts;
import com.itportal.culinary.portal.entity.Establishments;
import com.itportal.culinary.portal.repository.EstablishmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstablishmentsService {
    @Autowired
    EstablishmentsRepository establishmentsRepository;

    public List<Establishments> findAll(){
        return establishmentsRepository.findAll();
    }
    public Establishments findByName(String name){
        return establishmentsRepository.findByName(name);
    }

    public Establishments findById(long id){
        Optional<Establishments> establishments=establishmentsRepository.findById(id);
        if(establishments.isPresent()) {
            return establishments.get();
        }
        throw new RuntimeException();
    }
}
