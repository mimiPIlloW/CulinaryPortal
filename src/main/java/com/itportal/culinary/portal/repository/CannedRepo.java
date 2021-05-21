package com.itportal.culinary.portal.repository;

import com.itportal.culinary.portal.entity.Canned;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CannedRepo extends CrudRepository <Canned, Long> {
    List<Canned> findAll();
    Canned findByName(String name);
}
