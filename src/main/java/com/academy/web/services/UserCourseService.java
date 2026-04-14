package com.academy.web.services;

import java.util.List;

import com.academy.web.dtos.UserCourseDTO;
import com.academy.web.dtos.UserDTO;

public interface UserCourseService {

List<UserDTO> findUsers(String value);

List<UserCourseDTO> findStudentsByCourseId(Long id, String filtro);
    
}
