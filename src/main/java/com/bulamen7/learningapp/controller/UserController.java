package com.bulamen7.learningapp.controller;


import com.bulamen7.learningapp.model.User;
import com.bulamen7.learningapp.repository.UserRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private UserRepository userService;

    public UserController(UserRepository studentService) {
        this.userService = studentService;
    }

    @GetMapping("/hello-world/{name}")
    public String helloWorld(@PathVariable String name) {
        return "Hello " + name;
    }

    @PostMapping("/")
    public void createStudent(@RequestBody User user) {
        userService.saveUser(user);
    }

    @GetMapping("/")
    public List<User> findAllUsers() {
        return userService.findAll();
    }
    @GetMapping("/{id}")
    public User findUserById(@PathVariable int id) {
        return userService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void removeUser(@PathVariable int id) {
        userService.deleteUserById(id);
    }
}