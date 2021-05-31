package com.itportal.culinary.portal.repository;


import com.itportal.culinary.portal.entity.Soups;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SoupsRep extends CrudRepository<Soups, Long> {
    List<Soups> findAll();

    Soups findByName(String name);
}
