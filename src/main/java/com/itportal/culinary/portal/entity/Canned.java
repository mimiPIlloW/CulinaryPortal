package com.itportal.culinary.portal.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Canned {
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
    private String preparation;

    @ManyToOne
    private CookingRecipesGroup cookingRecipesGroup;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public String getAuthorName(){
        return author!= null ? author.getUsername() : "<none>";
    }
}
