package com.itportal.culinary.portal.service;

import com.itportal.culinary.portal.entity.Canned;
import com.itportal.culinary.portal.repository.CannedRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CannedService {
    @Autowired
    CannedRepo cannedRepo;

    public List<Canned> findAll() {
        return cannedRepo.findAll();
    }

    public Canned findByName(String name) {
        return cannedRepo.findByName(name);
    }

    public Canned findById(long id) {
        Optional<Canned> canned = cannedRepo.findById(id);
        if (canned.isPresent()) {
            return canned.get();
        }
        throw new RuntimeException();
    }
}
