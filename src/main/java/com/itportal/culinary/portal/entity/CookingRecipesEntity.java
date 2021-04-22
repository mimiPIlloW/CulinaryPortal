package com.itportal.culinary.portal.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class CookingRecipesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public CookingRecipesEntity() {
    }

    public CookingRecipesEntity(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
