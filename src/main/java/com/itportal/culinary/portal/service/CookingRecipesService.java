package com.itportal.culinary.portal.service;


import com.itportal.culinary.portal.entity.CookingRecipes;
import com.itportal.culinary.portal.repository.CookingRecipesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CookingRecipesService {
    @Autowired
    private CookingRecipesRepository cookingRecipesRepository;

    public CookingRecipes findByName(String name){
//        Optional<CookingRecipes> cookingRecipes=cookingRecipesRepository.findByName(name);
//        if (cookingRecipes.isPresent())
//            return cookingRecipes.get();
//        else throw new RuntimeException("Такой группы мышц нет");
        return null;
//        return cookingRecipesRepository.findByName(name);
    }

    public boolean createNewCookingRecipes(String name){
        CookingRecipes cookingRecipes=new CookingRecipes();
        cookingRecipes.setName(name);
        cookingRecipesRepository.save(cookingRecipes);
        return true;
    }

//    public boolean addMusclesIntoGroup(Muscle muscle, CookingRecipes muscleGroup){
//        muscleGroup.getMuscleSet().add(muscle);
//        muscleGroupRepository.save(muscleGroup);
//        return true;
//    }

    public List<CookingRecipes> findAll(){
        return cookingRecipesRepository.findAll();
    }

//    public CookingRecipes findMuscleGroupById(Long id){
//        Optional<CookingRecipes> muscleGroup=cookingRecipesRepository.findById(id);
//        if (muscleGroup.isPresent())
//            return muscleGroup.get();
//        else
//            throw new RuntimeException("Нет такой группы мышц");
//    }

    public boolean deleteCookingRecipes(CookingRecipes cookingRecipes){
        cookingRecipesRepository.delete(cookingRecipes);
        return true;
    }

}
