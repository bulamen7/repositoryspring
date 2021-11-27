package com.bulamen7.learningapp.model;


import org.hibernate.validator.constraints.pl.PESEL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Entity
public class User {
    //walidacja
    //klasa pesel z regexem
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String login;
    private String password;
    @Pattern(regexp = "^[A-Za-z]\\w{3,10}$")
    private String name;
    @Pattern(regexp = "^[A-Za-z]\\w{3,15}$")
    private String lastName;

    @PESEL
    private String personalNumber;
    private UserType type;

    public User(int id, String name, String lastName, String personalNumber, UserType type) {
        this.id = id;
        this.name = name;
        this.personalNumber = personalNumber;
        this.lastName = lastName;
        this.type = type;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", personalNumber='" + personalNumber + '\'' +
                ", type=" + type +
                '}';
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
}
