package com.bulamen7.learningapp.service;

import com.bulamen7.learningapp.model.Course;
import com.bulamen7.learningapp.model.User;
import com.bulamen7.learningapp.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class UserService{
    private final UserRepository userRepository;

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
        return userRepository.findById(id).map(user -> new User(user.getId(), user.getName(), user.getLastName(), user.getPersonalNumber(), user.getType())).get();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public void deleteUserById(int id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User with id " + id + " doesnt exist");
        } else {
            userRepository.deleteById(id);
        }
    }

    public Set<Course> getUserCourses(int id) {
        return userRepository.getById(id).getCourses();
    }

    //LISTA KURSOW UZYTKOWNIKA JEST CALY CZAS PUSTA
    public void addCourseToUser(Course course, int userId) {
        User user;
        if (userRepository.existsById(userId)) {
             user = userRepository.getById(userId);
            user.addCourse(course);
        } else throw new IllegalStateException("User doesnt exist");
    }

}