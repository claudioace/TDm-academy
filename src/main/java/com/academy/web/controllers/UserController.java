package com.academy.web.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.academy.web.dtos.UserDTO;
import com.academy.web.repositories.UserRepository;
import com.academy.web.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserService service;
    public UserController(UserRepository userRepository, UserService service) {
        this.userRepository = userRepository;
        this.service = service;
    }

    @GetMapping()
    public String getUsers(@RequestParam(required = false) String filtro, Model model){
        List<UserDTO> dtos = this.service.findUsers(filtro);
        model.addAttribute("users", dtos);
        model.addAttribute("filtro", filtro);
        

        return "users";
    

    }
    
    @GetMapping("/new")
    public String getNewUser(Model model){
        model.addAttribute("user", new UserDTO());
        return "formUser";
    }
    @PostMapping("/save")
    public String setUser(@ModelAttribute UserDTO user){
        this.service.saveUser(user);

        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String getUser(@PathVariable Long id, Model model){
        UserDTO user = this.service.getUserById(id);
        model.addAttribute("user", user);
        return "formUser";
    }
    
    /*@GetMapping("/delete/{id}")
    public String setDeleteUser(@PathVariable Long id, Model model){
        this.service.deleteUser(id);
        return "redirect:/users";
    }
    obs: Este delete con GET es para pruebas, Delete debe realiar con POST
    */

    @PostMapping("/deletePost")
    public String setDeleteUser(@RequestParam Long id){
        this.service.deleteUser(id);
        return "redirect:/users";
    }    
}
