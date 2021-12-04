package com.bulamen7.learningapp.model.dto.response;

import com.bulamen7.learningapp.model.User;

import java.util.HashSet;
import java.util.Set;

public class CourseResponseDto {
    private int id;
    private String name;
    private String description;
    private Set<User> users = new HashSet<>();


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}