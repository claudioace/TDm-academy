package com.academy.web.services;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academy.web.dtos.UserDTO;
import com.academy.web.entities.User;
import com.academy.web.repositories.UserRepository;



@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    
    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public List<UserDTO> findUsers(String value) {
        List<User> users;

        if (value != null && !value.isEmpty()) {
            users = this.repository.findByDniContainingOrFirstNameContainingOrLastNameContainingOrEmailContaining(value, value, value, value);
        } else {
            users = this.repository.findAll();
        }

        return users.stream()
                .map(u -> new UserDTO(
                    u.getId(),
                    u.getDni(),
                    u.getEmail(),
                    u.getFirstName(),
                    u.getLastName(),
                    u.getRole())
                )
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public void saveUser(UserDTO userDto) {
        User user = new User();

        if (userDto.getId() != null) {
            user = this.repository.findById(userDto.getId()).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            user.setId(userDto.getId());
        } else {
            user = new User();
        }

        user.setDni(userDto.getDni());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setRole(userDto.getRole());
        if (userDto.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }


        this.repository.save(user);
    }
    
    @Override
    public UserDTO getUserById(Long id) {
        User user = this.repository.findById(id).orElse(null);
        if (user == null) {
            return null; 
        }
        return new UserDTO(
            user.getId(),
            user.getDni(),
            user.getEmail(),
            user.getFirstName(),
            user.getLastName(),
            user.getRole()
        );
    }
    @Override
    public void deleteUser(Long id) {
        this.repository.deleteById(id);
    }
    @Override
    public void register(UserDTO userDto) {
        User user = new User();

        user.setDni(userDto.getDni());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole("STUDENT"); // posibles valoes: "STUDENT" | "ADMIN"

        this.repository.save(user);
    }

    @Override
    public UserDTO findbyEmail(String email) {
        Optional<User> userOptional = this.repository.findByEmail(email);
        User user = userOptional.orElseThrow();
        UserDTO dto = new UserDTO(
                                user.getId(),
                                user.getDni(),
                                user.getEmail(),
                                user.getFirstName(),
                                user.getLastName(),
                                user.getRole());
        return dto;
    }

}