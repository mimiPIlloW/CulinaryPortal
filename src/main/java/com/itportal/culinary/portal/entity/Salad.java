package com.itportal.culinary.portal.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Salad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private String time;
    private String servings;
    private String ennergyValue;
    private String image;
    private String ingridients;
    private String preparation;

    @ManyToOne
    private CookingRecipesGroup cookingRecipesGroup;

    @ManyToOne
    private User user;
}
