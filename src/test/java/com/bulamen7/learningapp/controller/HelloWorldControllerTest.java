package com.bulamen7.learningapp.controller;

import com.bulamen7.learningapp.model.User;
import com.bulamen7.learningapp.model.UserType;
import com.bulamen7.learningapp.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class HelloWorldControllerTest {
    User user = new User(1, "Dominika", "Sw", "51521521521", UserType.LECTURER);
    User user2 = new User(2, "Edek", "Sw", "51521521521", UserType.LECTURER);
    User user3 = new User(5, "Heniek", "Sw", "51521521521", UserType.LECTURER);
    // result.andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].name", is(user.getName())));
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserRepository userRepository;


    @Test
    void getMappingShouldReturnStatus200() throws Exception {
        mockMvc.perform(get("/users/")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getMappingShouldFindAllUsersAndReturnStatus200() throws Exception {
        Mockito.when(userRepository.findAll()).thenReturn(Arrays.asList(user, user2, user3));

        mockMvc.perform(MockMvcRequestBuilders.get("/users/")).
                andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*]", hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id", is(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].name", is("Heniek")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].type", is("LECTURER")));
    }

    @Test
    void getMappingWithPathVariableShouldFindUserByIdAndReturnStatus200() throws Exception {
        Mockito.when(userRepository.findById(5)).thenReturn(user3);

        mockMvc.perform( MockMvcRequestBuilders
                        .get("/users/{id}", 5)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Heniek"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.type").value("LECTURER"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Sw"));
    }

    @Test
    void getMappingWithPathVariableShouldReturnStatus200() throws Exception {

        mockMvc.perform(get("/users/hello-world/{name}", "ala")).
                andDo(print()).
                andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello ala")));
    }

    @Test
    void postMappingShouldReturnStatus200() throws Exception {
        String userMapper = new ObjectMapper().writeValueAsString(user);

        mockMvc.perform( MockMvcRequestBuilders
                        .post("/users/")
                        .content(userMapper)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

    }

    @Test
    void deleteMappingShouldDeleteUserById() throws Exception {
        mockMvc.perform(delete("/users/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted());
    }

}