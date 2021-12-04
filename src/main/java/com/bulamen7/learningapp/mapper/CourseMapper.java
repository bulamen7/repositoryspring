package com.bulamen7.learningapp.mapper;

import com.bulamen7.learningapp.model.Course;
import com.bulamen7.learningapp.model.dto.response.CourseResponseDto;

public class CourseMapper {
    public CourseResponseDto mapCourseToDto(Course course) {
        CourseResponseDto dto = new CourseResponseDto();
        dto.setId(course.getId());
        dto.setName(course.getName());
        dto.setDescription(course.getDescription());
        dto.setUsers(course.getUsers());
        return dto;
    }

    public Course mapDtoToCourse(CourseResponseDto courseResponseDto) {
        Course course = new Course();
        course.setId(courseResponseDto.getId());
        course.setName(courseResponseDto.getName());
        course.setDescription(courseResponseDto.getDescription());
        course.setUsers(courseResponseDto.getUsers());
        return course;
    }
}