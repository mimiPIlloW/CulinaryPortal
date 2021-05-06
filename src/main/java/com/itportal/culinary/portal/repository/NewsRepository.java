package com.itportal.culinary.portal.repository;

import java.util.List;

import com.itportal.culinary.portal.entity.NewsEntity;
import org.springframework.data.repository.CrudRepository;

public interface NewsRepository extends CrudRepository<NewsEntity, Long> {
    List<NewsEntity> findAll();
}
