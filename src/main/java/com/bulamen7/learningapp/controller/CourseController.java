package com.bulamen7.learningapp.controller;


import com.bulamen7.learningapp.mapper.CourseMapper;
import com.bulamen7.learningapp.model.Course;
import com.bulamen7.learningapp.model.User;
import com.bulamen7.learningapp.model.dto.request.CourseRequestDto;
import com.bulamen7.learningapp.model.dto.response.CourseResponseDto;
import com.bulamen7.learningapp.model.dto.response.UserResponseDto;
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

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/courses")

public class CourseController {
    private final CourseService courseService;
    private final CourseMapper courseMapper;


    public CourseController(CourseService courseService, CourseMapper courseMapper) {
        this.courseService = courseService;
        this.courseMapper = courseMapper;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public void createCourse(@RequestBody @Valid CourseRequestDto courseRequestDto) {
        Course course = courseMapper.mapRequestDtoToCourse(courseRequestDto);
        courseService.saveCourse(course);
    }

    @GetMapping("/{id}")
    public CourseResponseDto findCourseById(@PathVariable int id) throws NotFoundException {
        Course course = courseService.findById(id);
        return courseMapper.mapCourseToResponseDto(course);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteCourseById(@PathVariable int id) {
        courseService.deleteCourseById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{courseId}/students")
    public Set<UserResponseDto> studentCourses(@PathVariable int courseId) throws NotFoundException {
        return courseService.getAllUsers(courseId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{courseId}/students")
    public void subscribeStudentToCourse(@PathVariable int courseId, @RequestBody User user) throws NotFoundException {
        courseService.subscribeUserToCourse(user, courseId);
    }
}
