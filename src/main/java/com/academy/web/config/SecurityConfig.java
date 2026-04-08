package com.academy.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
            .requestMatchers(
                "/register",
                "/register/**",
                "/login",
                "/css/**")
            .permitAll()
            
            //si quiero que solo sea un unico rol con acceso:
            //  .requestMatchers("/users/**").hasRole("ADMIN")
            .requestMatchers("/api/**").permitAll()
            .requestMatchers("/studentPage/**").hasRole("STUDENT")
            .requestMatchers("/adminPage/**").hasRole("ADMIN")
            .anyRequest().authenticated())
            .formLogin(form -> form
                .loginPage("/login")
                .successHandler((request, response, authentication) -> {
                    String role = authentication.getAuthorities().iterator().next().getAuthority();
                    
                    if (role.equals("ROLE_ADMIN")) {
                        response.sendRedirect("/adminPage");
                    } else {
                        response.sendRedirect("/studentPage");
                    }
                })
                .permitAll())
            .logout(logout -> logout
                .logoutSuccessUrl("/logout?logout")
                .permitAll());

        return http.build();
                
    }
}
