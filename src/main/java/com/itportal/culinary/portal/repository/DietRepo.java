package com.itportal.culinary.portal.repository;


import com.itportal.culinary.portal.entity.Diet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DietRepo extends CrudRepository<Diet, Long> {
    List<Diet> findAll();

    Diet findByName(String name);
}
