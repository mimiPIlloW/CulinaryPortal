package com.itportal.culinary.portal.repository;

import com.itportal.culinary.portal.entity.Snacks;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SnacksRep  extends CrudRepository<Snacks, Long> {
    List<Snacks> findAll();
    Snacks findByName(String name);
}
