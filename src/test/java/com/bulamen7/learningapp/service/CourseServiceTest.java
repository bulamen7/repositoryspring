package com.bulamen7.learningapp.service;

import com.bulamen7.learningapp.model.Course;
import com.bulamen7.learningapp.model.User;
import com.bulamen7.learningapp.model.UserType;
import com.bulamen7.learningapp.repository.CourseRepository;
import com.bulamen7.learningapp.repository.UserRepository;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class CourseServiceTest {

    @MockBean
    CourseRepository courseRepository;
    @MockBean
    UserRepository userRepository;

    @Test
    void shouldSaveCourse() {
        CourseService courseService = new CourseService(courseRepository, userRepository);
        Course course = new Course("Mockito", "test");
        when(courseService.saveCourse(any(Course.class))).thenReturn(course);

        Course actualCourse = courseRepository.save(course);
        assertThat(actualCourse).isEqualTo(course);

    }

    @Test
    void shouldFindCourseById() throws NotFoundException {
        CourseService courseService = new CourseService(courseRepository, userRepository);
        Course course = new Course("Niemiecki", "Jezyk obcy");
        when(courseRepository.findById(1)).thenReturn(Optional.of(new Course("Niemiecki", "Jezyk obcy")));

        Course actualCourse = courseService.findById(1);
        assertThat(actualCourse).isEqualTo(course);
        assertThat(actualCourse.getName()).isEqualTo(course.getName());
    }

    @Test
    void shouldDeleteCourseById() {
        CourseService courseService = new CourseService(courseRepository, userRepository);
        courseService.deleteCourseById(5);
        verify(courseRepository).deleteById(any());
    }

    @Test
    void shouldSubscribeUserToCourse() {
        //given
        User user = new User("Arek", "Alkomat", "92012703631", UserType.ADMIN);
        Course course = new Course("kurs", "kursss");
        //when
        course.subscribeTo(new User("Arek", "Alkomat", "92012703631", UserType.ADMIN));
        //then
        assertThat(course.getUsers()).isEqualTo(Set.of(user));
    }

}
















