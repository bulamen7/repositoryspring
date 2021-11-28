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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/hello-world/{name}")
    public ResponseEntity<String> helloWorld(@PathVariable String name) {
        return status(HttpStatus.OK).body("Hello " + name);
    }

    @PostMapping("/")
    public ResponseEntity<User> createStudent(@RequestBody @Valid User user) {
        userRepository.saveUser(user);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> findAllUsers() {
        return status(HttpStatus.OK).body(userRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable int id) {
        return status(HttpStatus.OK).body(userRepository.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> removeUser(@PathVariable int id) {
        userRepository.deleteUserById(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
    }
}