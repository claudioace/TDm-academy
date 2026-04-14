package com.academy.web.services;

import java.util.List;

import com.academy.web.dtos.CourseDTO;

public interface CourseService {
    List<CourseDTO> findCourses(String value);

    void saveCourse(CourseDTO course);

    CourseDTO getCourseById(Long id);

    void deleteCourse(Long id);

    CourseDTO findbyName(String name);

}
