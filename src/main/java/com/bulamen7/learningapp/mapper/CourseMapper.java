package com.bulamen7.learningapp.mapper;

import com.bulamen7.learningapp.model.Course;
import com.bulamen7.learningapp.model.dto.request.CourseRequestDto;
import com.bulamen7.learningapp.model.dto.response.CourseResponseDto;
import org.springframework.stereotype.Service;

@Service
public class CourseMapper {
    public CourseRequestDto mapCourseToRequestDto(Course course) {
        CourseRequestDto dto = new CourseRequestDto();
        dto.setId(course.getId());
        dto.setName(course.getName());
        dto.setDescription(course.getDescription());
        dto.setUsers(course.getUsers());
        return dto;
    }

    public Course mapDtoToCourseRequest(CourseRequestDto courseRequestDto) {
        Course course = new Course();
        course.setId(courseRequestDto.getId());
        course.setName(courseRequestDto.getName());
        course.setDescription(courseRequestDto.getDescription());
        course.setUsers(courseRequestDto.getUsers());
        return course;
    }

    public CourseResponseDto mapCourseToResponseDto(Course course) {
        CourseResponseDto dto = new CourseResponseDto();
        dto.setId(course.getId());
        dto.setName(course.getName());
        dto.setDescription(course.getDescription());
        dto.setUsers(course.getUsers());
        return dto;
    }

    public Course mapDtoToCourseResponse(CourseResponseDto courseResponseDto) {
        Course course = new Course();
        course.setId(courseResponseDto.getId());
        course.setName(courseResponseDto.getName());
        course.setDescription(courseResponseDto.getDescription());
        course.setUsers(courseResponseDto.getUsers());
        return course;
    }
}