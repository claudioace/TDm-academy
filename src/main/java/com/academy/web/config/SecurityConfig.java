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
            .requestMatchers("/courses/**").hasAnyRole("STUDENT", "ADMIN")
            .requestMatchers("/users/**").hasRole("ADMIN")
            .requestMatchers("/myCourses/**").hasAnyRole("STUDENT", "ADMIN")
            .anyRequest().authenticated())
            .formLogin(form -> form
                .loginPage("/login")
                .successHandler((request, response, authentication) -> {
                    String role = authentication.getAuthorities().iterator().next().getAuthority();
                    
                    if (role.equals("ROLE_ADMIN")) {
                        request.getSession().setAttribute("isAdmin", true);
                        response.sendRedirect("/users");
                    } else {
                        request.getSession().setAttribute("isAdmin", false);
                        response.sendRedirect("/myCourses");
                    }
                })
                .permitAll())
                .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll());

        return http.build();
                
    }
}
