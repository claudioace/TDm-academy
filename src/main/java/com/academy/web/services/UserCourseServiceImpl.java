package com.academy.web.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.academy.web.dtos.UserCourseDTO;
import com.academy.web.dtos.UserDTO;
import com.academy.web.repositories.UserCourseRepository;

@Service
public class UserCourseServiceImpl implements UserCourseService{
    private final UserCourseRepository repository;
    public UserCourseServiceImpl(UserCourseRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<UserDTO> findUsers(String value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findUsers'");
    }

    @Override
    public List<UserCourseDTO> findStudentsByCourseId(Long id, String value) {
        //observcion: UserCourseDTO es diferente a UserCourseDTO.
        //no puedo aplicar List<UserCourse> usersCourses = método y poblar List<UserCourseDTO>
         if (value != null && !value.isEmpty()) {
        return this.repository.findStudentsByCourseIdFilter(id, value);
        } else {
        return this.repository.findStudentsByCourseId(id);
        }

        

    }
    
}
