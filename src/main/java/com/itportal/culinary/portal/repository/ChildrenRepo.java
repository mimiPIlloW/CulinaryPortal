package com.itportal.culinary.portal.repository;

import com.itportal.culinary.portal.entity.Children;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChildrenRepo extends CrudRepository<Children, Long> {
    List<Children> findAll();
    Children findByName(String name);
}
