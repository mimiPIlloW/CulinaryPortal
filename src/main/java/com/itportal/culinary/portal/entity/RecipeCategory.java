package com.itportal.culinary.portal.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class RecipeCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String category;

    @ManyToOne
    private CookingRecipesGroup cookingRecipesGroup;

    @ManyToOne
    private Recipes recipes;
}
