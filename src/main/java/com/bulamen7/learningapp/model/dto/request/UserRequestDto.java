package com.bulamen7.learningapp.model.dto.request;

import com.bulamen7.learningapp.model.Course;
import com.bulamen7.learningapp.model.UserType;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UserRequestDto {
    private int id;
    @Pattern(regexp = "^[A-Za-z]\\w{3,15}$", message = "min lenght 3, max 15")
    private String login;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")
    private String password;
    @Pattern(regexp = "^[A-Za-z]\\w{3,10}$", message = "min lenght 3, max 10")
    private String name;
    @Pattern(regexp = "^[A-Za-z]\\w{3,15}$", message = "min lenght 3, max 15")
    private String lastName;
    @Pattern(regexp = "^[0-9]{2}([02468]1|[13579][012])(0[1-9]|1[0-9]|2[0-9]|3[01])[0-9]{5}$", message = "Wrong PESEL")
    private String personalNumber;
    @Enumerated(EnumType.ORDINAL)
    private UserType type;
    private Set<Course> courses = new HashSet<>();


    public UserRequestDto() {
    }

    public UserRequestDto(@JsonProperty("name") String name, @JsonProperty("lastName") String lastName, @JsonProperty("personalNumber") String personalNumber, @JsonProperty("type") UserType type) {
        this.name = name;
        this.lastName = lastName;
        this.personalNumber = personalNumber;
        this.type = type;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        UserRequestDto that = (UserRequestDto) o;
        return id == that.id && Objects.equals(login, that.login) && Objects.equals(password, that.password) && Objects.equals(name, that.name) && Objects.equals(lastName, that.lastName) && Objects.equals(personalNumber, that.personalNumber) && type == that.type && Objects.equals(courses, that.courses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, name, lastName, personalNumber, type, courses);
    }

    @Override
    public String toString() {
        return "UserRequestDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", personalNumber='" + personalNumber + '\'' +
                ", type=" + type +
                ", courses=" + courses +
                '}';
    }
}