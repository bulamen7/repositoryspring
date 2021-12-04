package com.bulamen7.learningapp.service;

import com.bulamen7.learningapp.mapper.UserMapper;
import com.bulamen7.learningapp.model.Course;
import com.bulamen7.learningapp.model.User;
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
    private final UserMapper mapper;

    public UserService(UserRepository userRepository, UserMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Transactional
    public void saveUser(User user) {
        if (!userRepository.existsById(user.getId())) {
            userRepository.save(user);
        } else throw new IllegalStateException("Duplicated User");
    }

    public Optional<UserResponseDto> findById(int id) throws NotFoundException {
        if (!userRepository.existsById(id))
            throw new NotFoundException("User Not Found");
        return userRepository.findById(id).map(mapper::mapUserToDto);
    }

    public List<UserResponseDto> findAll() {
        return userRepository.findAll().stream().map(mapper::mapUserToDto).collect(Collectors.toList());
    }

    @Transactional
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

    public Set<Course> getUserCourses(int id) {
        return userRepository.getById(id).getCourses();
    }

    public void subscribeUserToCourse(Course course, int userId) {
        User user;
        if (userRepository.existsById(userId)) {
            user = userRepository.getById(userId);
            user.subscribeTo(course);
            userRepository.save(user);
        } else throw new IllegalStateException("User doesnt exist");
    }

}