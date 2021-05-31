package com.itportal.culinary.portal.repository;

import com.itportal.culinary.portal.entity.Sauces;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SaucesRepo extends CrudRepository<Sauces, Long> {
    List<Sauces> findAll();

    Sauces findByName(String name);
}
