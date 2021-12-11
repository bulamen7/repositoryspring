package com.bulamen7.learningapp.service;

import com.bulamen7.learningapp.mapper.CourseMapper;
import com.bulamen7.learningapp.mapper.UserMapper;
import com.bulamen7.learningapp.model.Course;
import com.bulamen7.learningapp.model.User;
import com.bulamen7.learningapp.model.dto.request.CourseRequestDto;
import com.bulamen7.learningapp.model.dto.response.CourseResponseDto;
import com.bulamen7.learningapp.model.dto.response.UserResponseDto;
import com.bulamen7.learningapp.repository.CourseRepository;
import com.bulamen7.learningapp.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper, UserMapper userMapper, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Transactional
    public CourseRequestDto saveCourse(CourseRequestDto courseRequestDto) {
        Course course = courseMapper.mapRequestDtoToCourse(courseRequestDto);
        Course saveCourse = courseRepository.save(course);
        return courseMapper.mapCourseToRequestDto(saveCourse);
    }

    public Optional<CourseResponseDto> findById(int id) throws NotFoundException {
        if (!courseRepository.existsById(id))
            throw new NotFoundException("Course Not Found");
        return courseRepository.findById(id).map(courseMapper::mapCourseToResponseDto);
    }

    @Transactional
    public void deleteCourseById(int id) {
        if (!courseRepository.existsById(id)) {
            throw new IllegalArgumentException("Course with given id doesnt exists");
        }
        courseRepository.deleteById(id);

    }

    public Set<UserResponseDto> studentsSavedOnCourse(int courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        return course.get().getUsers().stream()
                .map(userMapper::mapUserToResponseDto)
                .collect(Collectors.toSet());
    }

    public Set<CourseResponseDto> studentCourses(int userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.get().getCourses().stream().map(courseMapper::mapCourseToResponseDto).collect(Collectors.toSet());
    }


    public void subscribeCourseToUser(User user, int courseId) {
        Course course;
        if (courseRepository.existsById(courseId)) {
            course = courseRepository.getById(courseId);
            course.subscribeTo(user);
            courseRepository.save(course);
        } else throw new IllegalStateException("User doesnt exist");
    }
}