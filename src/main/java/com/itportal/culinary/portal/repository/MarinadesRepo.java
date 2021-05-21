package com.itportal.culinary.portal.repository;


import com.itportal.culinary.portal.entity.Marinades;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MarinadesRepo extends CrudRepository<Marinades, Long> {
    List<Marinades> findAll();
    Marinades findByName(String name);
}
