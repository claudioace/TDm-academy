package com.academy.web.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.academy.web.dtos.CourseDTO;
import com.academy.web.dtos.UserDTO;
import com.academy.web.services.CourseService;
import com.academy.web.services.UserService;

@RestController
@RequestMapping("/api")
public class ApiController {
    private final CourseService courseService;
    private final UserService userService;
    

    public ApiController(CourseService courseService, UserService userService) {
        this.courseService = courseService;
        this.userService = userService;
    }

    @GetMapping("/courses")
    public List<CourseDTO> getCourses(@RequestParam(required = false) String filter){
        
        List<CourseDTO> dtos = this.courseService.findCourses(filter);

        return dtos;
    }

    @GetMapping("/users")
    public List<UserDTO> getUsers(@RequestParam(required = false) String filter){
        
        List<UserDTO> dtos = this.userService.findUsers(filter);

        return dtos;
    }

}
