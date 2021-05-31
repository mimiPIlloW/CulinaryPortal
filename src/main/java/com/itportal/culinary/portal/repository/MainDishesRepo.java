package com.itportal.culinary.portal.repository;


import com.itportal.culinary.portal.entity.MainDishes;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MainDishesRepo extends CrudRepository<MainDishes, Long> {
    List<MainDishes> findAll();

    MainDishes findByName(String name);
}
