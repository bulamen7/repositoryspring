package com.bulamen7.learningapp.service;

import com.bulamen7.learningapp.mapper.CourseMapper;
import com.bulamen7.learningapp.model.Course;
import com.bulamen7.learningapp.model.dto.response.CourseResponseDto;
import com.bulamen7.learningapp.repository.CourseRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    @Transactional
    public void saveCourse(Course course) {
        courseRepository.save(course);
    }

    public Optional<CourseResponseDto> findById(int id) throws NotFoundException {
        if (!courseRepository.existsById(id))
            throw new NotFoundException("Course Not Found");
        return courseRepository.findById(id).map(courseMapper::mapCourseToDto);
    }

    public void deleteCourseById(int id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
        } else throw new IllegalArgumentException("Course with given id doesnt exists");
    }




}
