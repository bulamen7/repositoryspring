package com.bulamen7.learningapp.model.dto.response;

import com.bulamen7.learningapp.model.Course;
import com.bulamen7.learningapp.model.UserType;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UserResponseDto {
    private int id;
    private String name;
    private String lastName;
    private String personalNumber;
    private UserType type;
    private Set<Course> courses = new HashSet<>();

    public UserResponseDto() {
    }

    public UserResponseDto(int id, String name, String lastName, String personalNumber, UserType type, Set<Course> courses) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.personalNumber = personalNumber;
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

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserResponseDto that = (UserResponseDto) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(lastName, that.lastName) && Objects.equals(personalNumber, that.personalNumber) && type == that.type && Objects.equals(courses, that.courses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, personalNumber, type, courses);
    }

    @Override
    public String toString() {
        return "UserResponseDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", personalNumber='" + personalNumber + '\'' +
                ", type=" + type +
                ", courses=" + courses +
                '}';
    }
}