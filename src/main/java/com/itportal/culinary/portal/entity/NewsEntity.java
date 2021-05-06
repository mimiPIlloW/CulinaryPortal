package com.itportal.culinary.portal.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class NewsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String image;

    @ManyToOne
    private User user;
}
