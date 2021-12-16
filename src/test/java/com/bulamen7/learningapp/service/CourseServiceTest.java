package com.bulamen7.learningapp.service;

import com.bulamen7.learningapp.model.Course;
import com.bulamen7.learningapp.model.User;
import com.bulamen7.learningapp.model.UserType;
import javassist.NotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class CourseServiceTest {

    @Test
    void shouldSaveCourse() {
        CourseService courseService = mock(CourseService.class);
        Course course = new Course("Niemiecki", "Jezyk obcy");
        courseService.saveCourse(course);
        verify(courseService).saveCourse(course);
    }

    @Test
    void shouldFindCourseById() throws NotFoundException {
        CourseService courseService = mock(CourseService.class);
        Course course = new Course("Niemiecki", "Jezyk obcy");

        when(courseService.findById(5)).thenReturn(new Course("Niemiecki", "Jezyk obcy"));

        assertThat(courseService.findById(5)).isEqualTo(course);
    }

    @Test
    void shouldDeleteCourseById() {
        CourseService courseService = mock(CourseService.class);
        Course course = new Course("Niemiecki", "Jezyk obcy");

        courseService.deleteCourseById(course.getId());

        verify(courseService).deleteCourseById(course.getId());

        //then
        doThrow(new IllegalArgumentException("Course with given id doesnt exists")).when(courseService).saveCourse(course);
        Assertions.assertThatThrownBy(() -> courseService.saveCourse(course)).isInstanceOf(IllegalArgumentException.class).hasMessage("Course with given id doesnt exists");
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
















