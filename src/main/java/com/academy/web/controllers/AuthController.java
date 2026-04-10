package com.academy.web.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.academy.web.dtos.UserDTO;
import com.academy.web.services.UserService;

@Controller
public class AuthController {
    private final UserService service;
    
    public AuthController(UserService _service) {
        this.service = _service;
    }

    @GetMapping("/login")
    private String returnFormLogin(){
        return "login";
    }
    
    @GetMapping("/register")
    public String returnFormRegister(Model model){
        model.addAttribute("user", new UserDTO());
        return "register";
    }
    
    @PostMapping("/register")
    public String register(@ModelAttribute UserDTO user){
    this.service.register(user);

    return "redirect:/login";
    }
}