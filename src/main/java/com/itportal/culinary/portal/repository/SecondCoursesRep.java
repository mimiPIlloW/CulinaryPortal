package com.itportal.culinary.portal.repository;


import com.itportal.culinary.portal.entity.SecondCourses;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SecondCoursesRep extends CrudRepository<SecondCourses, Long> {
    List<SecondCourses> findAll();
    SecondCourses findByName(String name);
}
