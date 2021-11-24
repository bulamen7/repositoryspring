package com.bulamen7.learningapp.controller;

import com.bulamen7.learningapp.model.User;
import com.bulamen7.learningapp.model.UserType;
import com.bulamen7.learningapp.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@MockBean(UserRepository.class)
class HelloWorldControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private UserRepository userRepository;
    @Test
    void getMappingShouldReturnStatus200() throws Exception {

        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getMappingShouldFindAllUsersAndReturnStatus200() throws Exception {
        User user = new User(1, "Dominika", "Sw", "51521521521", UserType.TEACHER);
        User user2 = new User(2, "Edek", "Sw", "51521521521", UserType.TEACHER);
        User user3 = new User(5, "Heniek", "Sw", "51521521521", UserType.TEACHER);

        Mockito.when(userRepository.findAll()).thenReturn(Arrays.asList(user,user2,user3));

        mockMvc.perform(MockMvcRequestBuilders.get("/")).
                andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }
    @Test
    void getMappingWithPathVariableShouldFindUserByIdAndReturnStatus200() throws Exception {
        User user3 = new User(5, "Heniek", "Sw", "51521521521", UserType.TEACHER);

        Mockito.when(userRepository.findById(5)).thenReturn(user3);

        mockMvc.perform(get("/{id}",user3.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getMappingWithPathVariableShouldReturnStatus200() throws Exception {
        this.mockMvc.perform(get("/hello-world/{name}", "ala")).
                andDo(print()).
                andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello ala")));
    }

    @Test
    void postMappingShouldReturnStatus200() throws Exception {
        User user = new User(1, "Dominika", "Sw", "51521521521", UserType.TEACHER);
        String userMapper = new ObjectMapper().writeValueAsString(user);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userMapper))
                .andExpect(status().isCreated());

    }

    @Test
    void deleteMappingShouldDeleteUserById() throws Exception {
        User user = new User(1, "Dominika", "Sw", "51521521521", UserType.TEACHER);
        User user2 = new User(2, "Edek", "Sw", "51521521521", UserType.TEACHER);
        User user3 = new User(5, "Heniek", "Sw", "51521521521", UserType.TEACHER);

        mockMvc.perform(delete("/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
}