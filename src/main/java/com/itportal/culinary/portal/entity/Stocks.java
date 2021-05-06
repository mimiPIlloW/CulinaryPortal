package com.itportal.culinary.portal.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Stocks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String image;

    @ManyToOne
    private Establishments establishments;

    @ManyToOne
    private User user;
}
