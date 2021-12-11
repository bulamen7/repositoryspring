package com.bulamen7.learningapp.controller;

import com.bulamen7.learningapp.model.User;
import com.bulamen7.learningapp.model.dto.request.CourseRequestDto;
import com.bulamen7.learningapp.model.dto.response.CourseResponseDto;
import com.bulamen7.learningapp.model.dto.response.UserResponseDto;
import com.bulamen7.learningapp.service.CourseService;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public void createCourse(@RequestBody @Valid CourseRequestDto course) {
        courseService.saveCourse(course);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDto> findCourseById(@PathVariable int id) throws NotFoundException {
        return courseService.findById(id).
                map(ResponseEntity::ok).
                orElse(ResponseEntity.notFound().build());
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteCourseById(@PathVariable int id) {
        courseService.deleteCourseById(id);
    }

    @GetMapping("/{studentId}/courses")
    public ResponseEntity<Set<CourseResponseDto>> getStudentCourses(@PathVariable int studentId) {
        return new ResponseEntity<>(courseService.studentCourses(studentId), HttpStatus.OK);
    }

    @GetMapping("/{courseId}/students")
    public ResponseEntity<Set<UserResponseDto>> getStudentsSavedOnCourse(@PathVariable int courseId) {
        return new ResponseEntity<>(courseService.studentsSavedOnCourse(courseId),HttpStatus.OK);
    }

    @PostMapping("/{courseId}/students")
    public void saveStudentOnCourse(@RequestBody User user, @PathVariable int courseId) {
        courseService.subscribeCourseToUser(user, courseId);
    }

}


//POST /courses/{courseId}/students @RequestBody  {userId: 1}

//GET students/{studentId}/courses


//POST /courses/{courseId}/students
//body : {userId: 1}