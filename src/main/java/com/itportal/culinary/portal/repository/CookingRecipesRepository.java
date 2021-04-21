package com.itportal.culinary.portal.repository;

import java.util.List;
import java.util.Optional;

import com.itportal.culinary.portal.entity.CookingRecipes;
import org.springframework.data.repository.CrudRepository;

public interface CookingRecipesRepository extends CrudRepository<CookingRecipes, Long> {
    CookingRecipes findByName(String name);

    List<CookingRecipes> findAll();

    Optional<CookingRecipes> findById(Long id);

}
