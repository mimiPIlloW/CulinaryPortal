package com.itportal.culinary.portal.repository;


import com.itportal.culinary.portal.entity.Garnish;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GarnishRep extends CrudRepository<Garnish, Long> {
    List<Garnish> findAll();

    Garnish findByName(String name);
}
