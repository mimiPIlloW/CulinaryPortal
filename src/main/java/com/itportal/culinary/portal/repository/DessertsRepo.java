package com.itportal.culinary.portal.repository;


import com.itportal.culinary.portal.entity.Desserts;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DessertsRepo extends CrudRepository <Desserts, Long>{
    List<Desserts> findAll();
    Desserts findByName(String name);
}
