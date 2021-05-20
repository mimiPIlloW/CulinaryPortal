package com.itportal.culinary.portal.service;


import com.itportal.culinary.portal.entity.ForumEntity;
import com.itportal.culinary.portal.repository.ForumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ForumService {
    @Autowired
    ForumRepository forumRepository;

    public List<ForumEntity> findAll(){
        return forumRepository.findAll();
    }
    public ForumEntity findByName(String name){
        return forumRepository.findByName(name);
    }

    public ForumEntity findById(long id){
        Optional<ForumEntity> forum=forumRepository.findById(id);
        if(forum.isPresent()) {
            return forum.get();
        }
        throw new RuntimeException();
    }
}
