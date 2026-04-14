package com.academy.web.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academy.web.entities.Course;

public interface CourseRepository extends JpaRepository<Course,Long>{
    List<Course> findByNameContaining(String name);
    Optional<Course> findByName(String name);

}
    

