package com.academy.web.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDTO {
    private Long id;
    private String dni;
    private String email;
    private String firstName;
    private String lastName;
    @JsonIgnore
    private String password;
    public UserDTO(Long id, String dni, String email, String firstName, String lastName) {
        this.id = id;
        this.dni = dni;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public UserDTO() {
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
}
