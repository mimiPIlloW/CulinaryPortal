package com.itportal.culinary.portal.repository;


import com.itportal.culinary.portal.entity.Recipes;
import org.springframework.data.repository.CrudRepository;

public interface RecipesRepository extends CrudRepository<Recipes, Long> {
}
