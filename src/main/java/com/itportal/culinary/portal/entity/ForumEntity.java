package com.itportal.culinary.portal.entity;

import javax.persistence.*;

@Entity
public class ForumEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String full_text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public ForumEntity() {
    }

    public ForumEntity(String name, String full_text, User user) {
        this.name= name;
        this.full_text = full_text;
        this.author = user;
    }

    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
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

    public String getFull_text() {
        return full_text;
    }

    public void setFull_text(String full_text) {
        this.full_text = full_text;
    }
}