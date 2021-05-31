package com.itportal.culinary.portal.service;

import com.itportal.culinary.portal.entity.Children;
import com.itportal.culinary.portal.entity.Desserts;
import com.itportal.culinary.portal.repository.ChildrenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChildrenService {
    @Autowired
    ChildrenRepo childrenRepo;

    public List<Children> findAll() {
        return childrenRepo.findAll();
    }

    public Children findByName(String name) {
        return childrenRepo.findByName(name);
    }

    public Children findById(long id) {
        Optional<Children> children = childrenRepo.findById(id);
        if (children.isPresent()) {
            return children.get();
        }
        throw new RuntimeException();
    }
}
