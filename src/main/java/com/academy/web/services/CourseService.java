package com.academy.web.services;

import java.util.List;
import java.util.Map;

import com.academy.web.dtos.CourseDTO;

public interface CourseService {
    List<CourseDTO> findCourses(String value);

    void saveCourse(CourseDTO course);

    CourseDTO getCourseById(Long id);

    void deleteCourse(Long id);

    CourseDTO findbyName(String name);

    String getNameById(Long id);

    List<Map<String, Object>> findMyCourses(String username, String value);

}
