package com.itportal.culinary.portal.service;


import com.itportal.culinary.portal.entity.Picnic;
import com.itportal.culinary.portal.repository.PicnicRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PicnicService {
    @Autowired
    PicnicRepo picnicRepo;

    public List<Picnic> findAll() {
        return picnicRepo.findAll();
    }

    public Picnic findByName(String name) {
        return picnicRepo.findByName(name);
    }

    public Picnic findById(long id) {
        Optional<Picnic> picnic = picnicRepo.findById(id);
        if (picnic.isPresent()) {
            return picnic.get();
        }
        throw new RuntimeException();
    }
}
