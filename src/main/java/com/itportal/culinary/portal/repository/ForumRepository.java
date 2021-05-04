package com.itportal.culinary.portal.repository;

import com.itportal.culinary.portal.entity.ForumEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ForumRepository extends CrudRepository<ForumEntity, Long> {
}