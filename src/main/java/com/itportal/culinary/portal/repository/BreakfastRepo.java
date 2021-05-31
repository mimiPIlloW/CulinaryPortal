package com.itportal.culinary.portal.repository;

import com.itportal.culinary.portal.entity.Breakfast;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BreakfastRepo extends CrudRepository<Breakfast, Long> {
    List<Breakfast> findAll();

    Breakfast findByName(String name);
}
