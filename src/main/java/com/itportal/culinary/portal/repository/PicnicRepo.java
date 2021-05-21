package com.itportal.culinary.portal.repository;


import com.itportal.culinary.portal.entity.Picnic;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PicnicRepo extends CrudRepository<Picnic, Long> {
    List<Picnic> findAll();
    Picnic findByName(String name);
}
