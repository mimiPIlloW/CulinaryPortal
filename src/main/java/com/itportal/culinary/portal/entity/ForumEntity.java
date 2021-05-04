package com.itportal.culinary.portal.entity;

import javax.persistence.*;

@Entity
public class ForumEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;


    public ForumEntity() {
    }

    public ForumEntity(String name) {
        this.name= name;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}