package com.academy.web.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.academy.web.dtos.CourseDTO;
import com.academy.web.dtos.UserCourseDTO;
import com.academy.web.repositories.CourseRepository;
import com.academy.web.services.CourseService;
import com.academy.web.services.UserCourseService;

@Controller
@RequestMapping("/courses")
public class CourseController {
    private final CourseRepository courseRepository;
    private final CourseService service;
    private final UserCourseService userCourseService;
    
    public CourseController(CourseRepository courseRepository, CourseService service, UserCourseService userCourseService) {
        this.courseRepository = courseRepository;
        this.service = service;
        this.userCourseService = userCourseService;
    }
    
    @GetMapping()
    public String getCourses(@RequestParam(required = false) String filtro, Model model){
        List<CourseDTO> dtos = this.service.findCourses(filtro);
        model.addAttribute("courses", dtos);
        model.addAttribute("filtro", filtro);
        return "courses";
    }
    
    @GetMapping("/new")
    public String getNewCourse(Model model){
        CourseDTO dto = new CourseDTO();
        model.addAttribute("course", dto);
        return "formCourse";
    }

    @PostMapping("/save")
    public String setCourse(@ModelAttribute CourseDTO course, Model model){
        
    try {
        this.service.saveCourse(course);
        return "redirect:/courses";
    } catch (Exception e) {
        model.addAttribute("course", course);
        model.addAttribute("errorMessage", "Curso ya existe con ese nombre");
        return "formCourse";
    }
    }

    @GetMapping("/edit/{id}")
    public String getCourse(@PathVariable Long id, Model model){
        CourseDTO course = this.service.getCourseById(id);
        model.addAttribute("course", course);
        return "formCourse";
    }    

    @PostMapping("/deletePost")
    public String setDeleteCourse(@RequestParam Long id){
        this.service.deleteCourse(id);
        return "redirect:/courses";
    }
    
    @GetMapping("/students/{id}")
    public String getStudentsByCourses(
        @PathVariable Long id,
        @RequestParam(required = false) String filtro,
        Model model){
        
        List<UserCourseDTO> students = this.userCourseService.findStudentsByCourseId(id, filtro);
        model.addAttribute("courseName", this.service.getNameById(id));
        model.addAttribute("courseId", id);
        model.addAttribute("students", students);
        return "studentsCourse";
    }

    @GetMapping("/myCourses")
    public String getMyCourses(
            @RequestParam(required = false) String filtro,
            Model model,
            Authentication auth){
        
        String myUsername = auth.getName();
        List<Map<String, Object>> myCourses = service.findMyCourses(myUsername, filtro);
        model.addAttribute("myCourses", myCourses);
        model.addAttribute("filtro", filtro);


        return "myCourses";
    }    
    
    @GetMapping("/myCourses/{courseId}")
    public String getCourseDetails(
        @PathVariable Long courseId,
        Authentication auth,
        Model model){
        
        String myUsername = auth.getName();
        List<Map<String, Object>> myCourseDetails = service.findMyCourseDetails(myUsername, courseId);
        model.addAttribute("myCourseDetails", myCourseDetails);

        System.out.println(myUsername);
        System.out.println(myCourseDetails);
        return "myCoursesDetails";
    }

}
