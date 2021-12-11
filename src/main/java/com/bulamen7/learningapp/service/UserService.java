package com.bulamen7.learningapp.service;

import com.bulamen7.learningapp.mapper.CourseMapper;
import com.bulamen7.learningapp.mapper.UserMapper;
import com.bulamen7.learningapp.model.Course;
import com.bulamen7.learningapp.model.User;
import com.bulamen7.learningapp.model.dto.request.UserRequestDto;
import com.bulamen7.learningapp.model.dto.response.CourseResponseDto;
import com.bulamen7.learningapp.model.dto.response.UserResponseDto;
import com.bulamen7.learningapp.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CourseMapper courseMapper;

    public UserService(UserRepository userRepository, UserMapper mapper, CourseMapper courseMapper) {
        this.userRepository = userRepository;
        this.userMapper = mapper;
        this.courseMapper = courseMapper;
    }

    @Transactional
    public UserRequestDto saveUser(UserRequestDto userRequestDto) {
        User user = userMapper.mapRequestDtoToUser(userRequestDto);
        User saveUser = userRepository.save(user);
        return userMapper.mapUserToRequestDto(saveUser);
    }

    public Optional<UserResponseDto> findById(int id) throws NotFoundException {
        if (!userRepository.existsById(id))
            throw new NotFoundException("User Not Found");
        return userRepository.findById(id)
                .map(userMapper::mapUserToResponseDto);
    }

    public List<UserResponseDto> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::mapUserToResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

    public Set<CourseResponseDto> getUserCourses(int id) {
        return userRepository.getById(id).getCourses().stream()
                .map(courseMapper::mapCourseToResponseDto)
                .collect(Collectors.toSet());
    }

    public void subscribeUserToCourse(Course course, int userId) {
        User user;
        if (userRepository.existsById(userId)) {
            user = userRepository.getById(userId);
            user.subscribeTo(course);
            userRepository.save(user);
        } else throw new IllegalStateException("Course doesnt exist");
    }



}