package com.bulamen7.learningapp.model.dto.request;

import com.bulamen7.learningapp.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CourseRequestDto {
        private int id;
        @Pattern(regexp = "^[a-zA-Z0-9]\\w{3}", message = "length must be atleast 3")
        private String name;
        @Pattern(regexp = "^[a-zA-Z0-9]\\w{3}", message = "length must be atleast 3")
        private String description;
        private Set<User> users = new HashSet<>();

    public CourseRequestDto() {
    }

    public CourseRequestDto(@JsonProperty("id") int id, @JsonProperty("name")String name, @JsonProperty("description") String description, @JsonProperty("users") Set<User> users) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.users = users;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseRequestDto that = (CourseRequestDto) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(users, that.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, users);
    }

    @Override
    public String toString() {
        return "CourseRequestDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", users=" + users +
                '}';
    }
}
