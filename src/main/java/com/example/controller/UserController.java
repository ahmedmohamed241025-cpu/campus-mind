package com.example.controller;
import com.example.entity.*;
import com.example.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) { this.userService = userService; }

    @PostMapping("/create")
    public User createUser(@RequestBody User user) { return userService.saveUser(user); }

    @PostMapping("/student")
    public Student createStudent(@RequestBody Student student) { return userService.saveStudent(student); }
}