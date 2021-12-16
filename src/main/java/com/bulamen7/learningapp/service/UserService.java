package com.bulamen7.learningapp.service;

import com.bulamen7.learningapp.mapper.CourseMapper;
import com.bulamen7.learningapp.model.Course;
import com.bulamen7.learningapp.model.User;
import com.bulamen7.learningapp.model.dto.response.CourseResponseDto;
import com.bulamen7.learningapp.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final CourseMapper courseMapper = new CourseMapper();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveUser(User user) {
        if (!userRepository.existsById(user.getId())) {
            userRepository.save(user);
        } else throw new IllegalStateException("Duplicated User");
    }

    public User findById(int id) throws NotFoundException {
        if (!userRepository.existsById(id))
            throw new NotFoundException("User Not Found");
        return userRepository.findById(id).get();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

    public void subscribeCourseToUser(Course course, int userId) {
        User user = userRepository.getById(userId);
        user.subscribeTo(course);
    }

    public Set<CourseResponseDto> getAllCourses(int userId) {
        return userRepository.getById(userId).getCourses().stream().map(courseMapper::mapCourseToResponseDto).collect(Collectors.toSet());
    }
}