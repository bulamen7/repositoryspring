package com.bulamen7.learningapp.service;

import com.bulamen7.learningapp.model.Course;
import com.bulamen7.learningapp.repository.CourseRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Transactional
    public void saveCourse(Course course) {
        courseRepository.save(course);
    }

    public Course findById(int id) throws NotFoundException {
        if (!courseRepository.existsById(id))
            throw new NotFoundException("Course Not Found");
        return courseRepository.findById(id).map(course -> new Course(course.getId(), course.getName(), course.getDescription(), course.getUser())).get();
    }

    public void deleteCourseById(int id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
        } else throw new IllegalArgumentException("Course with given id doesnt exists");
    }



}
