package com.bulamen7.learningapp.model.dto.response;

import com.bulamen7.learningapp.model.Course;
import com.bulamen7.learningapp.model.UserType;

import java.util.HashSet;
import java.util.Set;

public class UserResponseDto {
    private int id;
    private String name;
    private String lastName;
    private UserType type;
    private Set<Course> courses = new HashSet<>();

    public UserResponseDto() {
    }

    public UserResponseDto(String name, String lastName, UserType type, Set<Course> courses) {
        this.name = name;
        this.lastName = lastName;
        this.type = type;
        this.courses = courses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}