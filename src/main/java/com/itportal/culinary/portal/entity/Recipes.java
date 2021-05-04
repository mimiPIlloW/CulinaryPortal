package com.itportal.culinary.portal.entity;

import javax.persistence.*;

@Entity
public class Recipes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private String ingridients;
    private String time;
    private String servings;
    private String ennergyValue;
    private String image;

    @ManyToOne
    private CookingRecipesGroup cookingRecipesGroup;
}
