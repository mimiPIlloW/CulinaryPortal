package com.itportal.culinary.portal.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Establishments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private String address;
    private String opening_hours;
    private String telephone;
    private String image;

    @ManyToOne
    private Cities cities;
}
