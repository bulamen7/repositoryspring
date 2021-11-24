package com.bulamen7.learningapp.model;


import java.util.Objects;


public class User {

    private int id;

    private String login;
    private String password;
    private String name;
    private String lastName;
    private String personalNumber;
    private UserType type;

    public User() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public UserType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", personalNumber='" + personalNumber + '\'' +
                '}';
    }

    public User(int id, String name, String lastName, String personalNumber, UserType type) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.personalNumber = personalNumber;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(name, user.name) && Objects.equals(lastName, user.lastName) && Objects.equals(personalNumber, user.personalNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, name, lastName, personalNumber);
    }
}
