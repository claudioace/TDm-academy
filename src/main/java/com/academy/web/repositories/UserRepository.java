package com.academy.web.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academy.web.entities.User;

public interface UserRepository  extends JpaRepository<User, Long>{

    List<User> findByFirstNameContaining(String firstName);
    Optional<User> findByEmail(String email);
}
