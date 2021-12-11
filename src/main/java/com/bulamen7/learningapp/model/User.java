package com.bulamen7.learningapp.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String login;
    private String password;
    private String name;
    private String lastName;
    private String personalNumber;
    private UserType type;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "users_courses", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "course_id")})
    @JsonProperty("courses")
    private Set<Course> courses = new HashSet<>();

    public User() {
    }

    public User(String name, String lastName, String personalNumber, UserType type) {
        this.name = name;
        this.lastName = lastName;
        this.personalNumber = personalNumber;
        this.type = type;
    }


    public User subscribeTo(Course course) {
        courses.add(course);
        return this;
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

    public String toString() {
        return "User{" + "id=" + id + ", name='" + name + '\'' + ", lastName='" + lastName + '\'' + ", personalNumber='" + personalNumber + '\'' + ", type=" + type + '}';
    }

}
