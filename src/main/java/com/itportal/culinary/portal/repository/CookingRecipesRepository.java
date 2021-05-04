package com.itportal.culinary.portal.repository;

import com.itportal.culinary.portal.entity.CookingRecipesGroup;
import org.springframework.data.repository.CrudRepository;

public interface CookingRecipesRepository extends CrudRepository<CookingRecipesGroup, Long> {
}
