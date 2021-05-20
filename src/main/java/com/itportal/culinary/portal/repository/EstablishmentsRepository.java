package com.itportal.culinary.portal.repository;


import com.itportal.culinary.portal.entity.Establishments;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EstablishmentsRepository extends CrudRepository<Establishments, Long> {
    List<Establishments> findAll();
    Establishments findByName(String name);
}
