package com.academy.web.dtos;

import java.time.LocalDate;

public class UserCourseDTO {

    private Long id;
    private Long userId;
    private Long courseId;
    private String dni;
    private String firstName;
    private String lastName;
    private String courseName;
    private LocalDate date;
    private Double avgGrade;
    
    public UserCourseDTO(Long id, Long userId, Long courseId, String dni, String firstName, String lastName,
            String courseName, LocalDate date, Double avgGrade) {
        this.id = id;
        this.userId = userId;
        this.courseId = courseId;
        this.dni = dni;
        this.firstName = firstName;
        this.lastName = lastName;
        this.courseName = courseName;
        this.date = date;
        this.avgGrade = avgGrade;
    }



    public UserCourseDTO() {}


    
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getCourseId() {
        return courseId;
    }
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public Double getAvgGrade() {
        return avgGrade;
    }
    public void setAvgGrade(Double avgGrade) {
        this.avgGrade = avgGrade;
    }


}
