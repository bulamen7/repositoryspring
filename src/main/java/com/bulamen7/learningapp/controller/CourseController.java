package com.bulamen7.learningapp.controller;

import com.bulamen7.learningapp.model.Course;
import com.bulamen7.learningapp.service.CourseService;
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

@RestController
@RequestMapping("/course")

public class CourseController {
    CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public void createStudent(@RequestBody Course course) {
        courseService.saveCourse(course);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Course findCourseById(@PathVariable int id) throws NotFoundException {
        return courseService.findById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteCourseById(@PathVariable int id){
        courseService.deleteCourseById(id);
    }
}
