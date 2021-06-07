package com.itportal.culinary.portal.repository;

import com.itportal.culinary.portal.entity.Marinades;
import com.itportal.culinary.portal.entity.NewsEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NewsRepository extends CrudRepository<NewsEntity, Long> {
    List<NewsEntity> findAll();

    NewsEntity findByName(String name);
}
