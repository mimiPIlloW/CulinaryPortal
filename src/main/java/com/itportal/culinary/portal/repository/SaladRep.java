package com.itportal.culinary.portal.repository;

import com.itportal.culinary.portal.entity.Salad;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SaladRep extends CrudRepository<Salad, Long> {
    List<Salad> findAll();
    Salad findByName(String name);
}
