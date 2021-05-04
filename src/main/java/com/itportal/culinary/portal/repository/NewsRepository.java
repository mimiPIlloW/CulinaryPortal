package com.itportal.culinary.portal.repository;

import java.util.List;
import java.util.Optional;

import com.itportal.culinary.portal.entity.newsEntity;
import org.springframework.data.repository.CrudRepository;

public interface NewsRepository extends CrudRepository<newsEntity, Long> {
    List<newsEntity> findAll();
}
