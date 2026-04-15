package com.academy.web.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academy.web.dtos.CourseDTO;
import com.academy.web.entities.Course;
import com.academy.web.repositories.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService{
    private final CourseRepository repository;

    public CourseServiceImpl(CourseRepository repository) {
        this.repository = repository;
    }

    @Override
    public void deleteCourse(Long id) {
        this.repository.deleteById(id);
    }

    @Override
    public List<CourseDTO> findCourses(String value) {
        List<Course> courses;
        if (value != null && !value.isEmpty()) {
            courses = this.repository.findByNameContaining(value);
        } else {
            courses = this.repository.findAll();
        }
        return courses.stream()
            .map(c -> new CourseDTO(
                c.getId(),
                c.getName(),
                c.getDescription())
            )
            .collect(Collectors.toList());
    }

    @Override
    public CourseDTO findbyName(String name) {
        Optional<Course> courseOptional = this.repository.findByName(name);
        Course course = courseOptional.orElseThrow();
        CourseDTO dto = new CourseDTO(
                                    course.getId(),
                                    course.getName(),
                                    course.getDescription());
        return dto;
    }

    @Override
    public CourseDTO getCourseById(Long id) {
        Course course = this.repository.findById(id).orElse(null);
        if (course == null) {
            return null;
        } else {
            return new CourseDTO(
                course.getId(),
                course.getName(),
                course.getDescription()
            );
        }
    }

    @Override
    @Transactional
    public void saveCourse(CourseDTO courseDto) {
        Course course = new Course();

        if (courseDto.getId() != null) {
            course = this.repository.findById(courseDto.getId()).orElseThrow(() -> new RuntimeException("Curso no encontrado"));
            course.setId(courseDto.getId());
        } else {
            course = new Course();
        }
        course.setName(courseDto.getName());
        course.setDescription(courseDto.getDescription());
        this.repository.save(course);
        
    }

    @Override
    public String getNameById(Long id) {
        String name = this.repository.getNameById(id).orElse(null);
        if (name == null) {
            return "Asignatura";
        } else {
            return name;
        }
    }

    @Override
    public List<Map<String, Object>> findMyCourses(String username, String value) {
        List<Map<String, Object>> myCourses = this.repository.findMyCourses(username, value);
        return myCourses;

    }
    
}
