package com.academy.web.services;

import java.util.List;

import com.academy.web.dtos.UserDTO;

public interface UserService {
    List<UserDTO> findUsers(String value);

    void saveUser(UserDTO user);

    UserDTO getUserById(Long id);

    void deleteUser(Long id);

    void register(UserDTO user);

    UserDTO findbyEmail(String email);
    
}