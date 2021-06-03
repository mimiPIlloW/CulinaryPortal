package com.itportal.culinary.portal.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ForumEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String anons;
    private String full_text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public String getAuthorName(){
        return author!= null ? author.getUsername() : "<none>";
    }
}