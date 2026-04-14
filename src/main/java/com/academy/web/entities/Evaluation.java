package com.academy.web.entities;

import java.time.LocalDate;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "evaluations")
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_course_id", nullable =false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserCourse userCourseId;
    
    @Column(name = "grade", nullable = false)
    private Double grade; 
    
    @Column(name = "date", nullable = false)
    private LocalDate date;

    public Evaluation() {
    }

}