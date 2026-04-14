package com.academy.web.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.academy.web.dtos.UserCourseDTO;
import com.academy.web.entities.UserCourse;

@Repository
public interface UserCourseRepository extends JpaRepository<UserCourse, Long> {
    
    @Query("""
    SELECT new com.academy.web.dtos.UserCourseDTO(
        uc.id,
        uc.user.id,
        uc.course.id,
        uc.user.dni,
        uc.user.firstName,
        uc.user.lastName,
        uc.course.name,
        uc.date,
        COALESCE(AVG(e.grade), 0.0)
    )
    FROM UserCourse uc 
    LEFT JOIN Evaluation e ON e.userCourseId.id = uc.id
    WHERE uc.course.id = :courseId
    GROUP BY uc.id, uc.user.id, uc.course.id, uc.user.firstName, 
             uc.user.lastName, uc.course.name, uc.date
    """)
    List<UserCourseDTO> findStudentsByCourseId(@Param("courseId") Long courseId);

    @Query("""
        SELECT new com.academy.web.dtos.UserCourseDTO(
            uc.id,
            uc.user.id,
            uc.course.id,
            uc.user.dni,
            uc.user.firstName,
            uc.user.lastName,
            uc.course.name,
            uc.date,
            COALESCE(AVG(e.grade), 0.0)
        )
        FROM UserCourse uc 
        LEFT JOIN Evaluation e ON e.userCourseId.id = uc.id
        WHERE uc.course.id = :courseId 
          AND (UPPER(uc.user.dni) LIKE UPPER(CONCAT('%', :value, '%')) OR
               UPPER(uc.user.firstName) LIKE UPPER(CONCAT('%', :value, '%')) OR
               UPPER(uc.user.lastName) LIKE UPPER(CONCAT('%', :value, '%')))
        GROUP BY uc.id, uc.user.id, uc.course.id, uc.user.firstName, 
                 uc.user.lastName, uc.course.name, uc.date
        """)
    List<UserCourseDTO> findStudentsByCourseIdFilter(@Param("courseId") Long courseId, @Param("value") String value);
}
