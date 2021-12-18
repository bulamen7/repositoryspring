package com.bulamen7.learningapp.controller;

import com.bulamen7.learningapp.model.User;
import com.bulamen7.learningapp.model.UserType;
import com.bulamen7.learningapp.repository.UserRepository;
import com.bulamen7.learningapp.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ObjectMapper objectMapper;


    @Test
    void shouldReturnAllUsers() {
        //given
        User user = new User("Dominika", "Swiokl", "51521521521", UserType.LECTURER);
        userService.saveUser(user);
        //when
        ResponseEntity<User[]> result = testRestTemplate.getForEntity("http://localhost:" + port + "/users", User[].class);
        //then
        assertEquals(result.getStatusCodeValue(), 200);
        assertThat(result.getBody()).contains(user);
    }

    @Test
    void shouldReturnUserById() {
        //given
        User user = new User("Edek", "Swiokl", "51521521521", UserType.LECTURER);
        userService.saveUser(user);
        String baseUrl = "http://localhost:" + port + "/users/" + user.getId();
        //when
        ResponseEntity<User> result = testRestTemplate.getForEntity(baseUrl, User.class);
        //then
        assertEquals(result.getStatusCodeValue(), 200);
        assertThat(result.getBody()).isEqualTo(user);

    }

    @Test
    void shouldCreateUser() {
        //given
        User user = new User("Henryk", "Swiokl", "51521521521", UserType.LECTURER);
        String baseUrl = "http://localhost:" + port + "/users/";
        HttpEntity<User> request = new HttpEntity<>(user);
        //when
        ResponseEntity<User> result = testRestTemplate.postForEntity(baseUrl, request, User.class);
        //then
        assertEquals(result.getStatusCodeValue(), 201);
    }

}

