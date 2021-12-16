package com.bulamen7.learningapp.service;

import com.bulamen7.learningapp.mapper.UserMapper;
import com.bulamen7.learningapp.model.Course;
import com.bulamen7.learningapp.model.User;
import com.bulamen7.learningapp.model.dto.response.UserResponseDto;
import com.bulamen7.learningapp.repository.CourseRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final UserMapper userMapper;

    public CourseService(CourseRepository courseRepository, UserMapper userMapper) {
        this.courseRepository = courseRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    public void saveCourse(Course course) {
        courseRepository.save(course);
    }

    public Course findById(int id) throws NotFoundException {
        if (!courseRepository.existsById(id))
            throw new NotFoundException("Course Not Found");
        return courseRepository.findById(id).get();
    }

    public void deleteCourseById(int id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
        } else throw new IllegalArgumentException("Course with given id doesnt exists");
    }

    public void subscribeUserToCourse(User user, int courseId) {
        Course course = courseRepository.getById(courseId);
        course.subscribeTo(user);
    }

    public Set<UserResponseDto> getAllUsers(int courseId) {
        return courseRepository.getById(courseId).getUsers().stream().map(userMapper::mapUserToResponseDto).collect(Collectors.toSet());
    }
}
