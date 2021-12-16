package com.bulamen7.learningapp.controller;


import com.bulamen7.learningapp.mapper.CourseMapper;
import com.bulamen7.learningapp.mapper.UserMapper;
import com.bulamen7.learningapp.model.Course;
import com.bulamen7.learningapp.model.User;
import com.bulamen7.learningapp.model.dto.request.UserRequestDto;
import com.bulamen7.learningapp.model.dto.response.CourseResponseDto;
import com.bulamen7.learningapp.model.dto.response.UserResponseDto;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final CourseMapper courseMapper;

    public UserController(UserService userService, UserMapper userMapper, CourseMapper courseMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.courseMapper = courseMapper;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public void createUser(@RequestBody @Valid UserRequestDto userRequestDto) {
        User user = userMapper.mapRequestDtoToUser(userRequestDto);
        userService.saveUser(user);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<UserResponseDto> findAllUsers() {
        return userService.findAll().stream().map(userMapper::mapUserToResponseDto).collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public UserResponseDto findById(@PathVariable int id) throws NotFoundException {
        User user = userService.findById(id);
        return userMapper.mapUserToResponseDto(user);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{id}")
    public void removeUserById(@PathVariable int id) {
        userService.deleteUserById(id);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{userId}/courses")
    public void subscribeStudentToCourse(@PathVariable int userId, @RequestBody Course course) throws NotFoundException {
        userService.subscribeCourseToUser(course, userId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{userId}/courses")
    public Set<CourseResponseDto> userCourses(@PathVariable int userId) throws NotFoundException {
        return userService.getAllCourses(userId);
    }
}
