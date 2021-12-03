package com.bulamen7.learningapp.controller;


import com.bulamen7.learningapp.model.Course;
import com.bulamen7.learningapp.model.User;
import com.bulamen7.learningapp.service.UserService;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/hello-world/{name}")
    public String helloWorld(@PathVariable String name) {
        return "Hello " + name;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public void createStudent(@RequestBody @Valid User user) {
        userService.saveUser(user);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<User> findAllUsers() {
        return userService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public User findUserById(@PathVariable int id) throws NotFoundException {
        return userService.findById(id);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{id}")
    public void removeUserById(@PathVariable int id) {
        userService.deleteUserById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}/courses")
    public Set<Course> getCourses(@PathVariable int id) {
        return userService.getUserCourses(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}/courses")
    public void addCoursesToUser(@PathVariable int id, @RequestBody Course course) {
        userService.addCourseToUser(course,id);
    }
}