package com.itportal.culinary.portal.repository;

import java.util.List;
import java.util.Optional;

import com.itportal.culinary.portal.entity.CookingRecipesEntity;
import org.springframework.data.repository.CrudRepository;

public interface CookingRecipesRepository extends CrudRepository<CookingRecipesEntity, Long> {
}
