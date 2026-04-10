package com.academy.web.services;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.academy.web.entities.User;
import com.academy.web.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{    
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = this.userRepository.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException("credenciales inválidas"));
        
        return new org.springframework.security.core.userdetails.User(
            user.getEmail(),
            user.getPassword(),
        Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getRole())));
    }

}
