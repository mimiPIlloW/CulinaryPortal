package com.itportal.culinary.portal.repository;


import com.itportal.culinary.portal.entity.Drinks;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DrinksRep extends CrudRepository<Drinks, Long> {
    List<Drinks> findAll();
    Drinks findByName(String name);
}
