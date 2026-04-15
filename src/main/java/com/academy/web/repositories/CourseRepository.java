package com.academy.web.repositories;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.academy.web.entities.Course;

public interface CourseRepository extends JpaRepository<Course,Long>{
    List<Course> findByNameContaining(String name);
    Optional<Course> findByName(String name);
    
    @Query("SELECT c.name FROM Course c WHERE c.id = :id")
    Optional<String> getNameById(@Param("id") Long id);

    @Query("""
        SELECT new map(
            c.id as courseId,
            c.name as courseName,
            uc.date as date,
            COALESCE(AVG(e.grade), 0.0) as avgGrade
        )
        FROM Course c
        JOIN UserCourse uc ON c.id = uc.course.id
        LEFT JOIN Evaluation e ON uc.id = e.userCourseId.id
        JOIN User u ON uc.user.id = u.id
        WHERE u.email = :username
        AND (:filtro IS NULL OR c.name LIKE %:filtro%)
        GROUP BY c.id, c.name, uc.date
        ORDER BY uc.date DESC
        """)
    List<Map<String, Object>> findMyCourses(@Param("username") String username, @Param("filtro") String filtro);


}
    

