package com.itportal.culinary.portal.service;


import com.itportal.culinary.portal.entity.SecondCourses;
import com.itportal.culinary.portal.repository.SecondCoursesRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SecondCoursesService {
    @Autowired
    SecondCoursesRep secondCoursesRep;

    public List<SecondCourses> findAll() {
        return secondCoursesRep.findAll();
    }

    public SecondCourses findByName(String name) {
        return secondCoursesRep.findByName(name);
    }

    public SecondCourses findById(long id) {
        Optional<SecondCourses> secondCourses = secondCoursesRep.findById(id);
        if (secondCourses.isPresent()) {
            return secondCourses.get();
        }
        throw new RuntimeException();
    }

}
