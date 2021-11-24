package com.bulamen7.learningapp.controller;


import com.bulamen7.learningapp.model.User;
import com.bulamen7.learningapp.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
public class UserController {
    private UserRepository userService;

    public UserController(UserRepository studentService) {
        this.userService = studentService;
    }

    @GetMapping("/hello-world/{name}")
    public ResponseEntity<String> helloWorld(@PathVariable String name) {
        return  status(HttpStatus.OK).body("Hello " + name);
    }

    @PostMapping("/")
    public ResponseEntity<Void> createStudent(@RequestBody User user) {
        userService.saveUser(user);
       return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> findAllUsers() {
        return status(HttpStatus.OK).body(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable int id) {
        return status(HttpStatus.OK).body(userService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeUser(@PathVariable int id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}