package com.bulamen7.learningapp.controller;

import com.bulamen7.learningapp.mapper.CourseMapper;
import com.bulamen7.learningapp.mapper.UserMapper;
import com.bulamen7.learningapp.model.User;
import com.bulamen7.learningapp.model.UserType;
import com.bulamen7.learningapp.model.dto.response.UserResponseDto;
import com.bulamen7.learningapp.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTests {
    User user = new User("Dominika", "Swiokl", "92012703631", UserType.LECTURER);
    User user2 = new User("Edek", "Sw", "51521521521", UserType.LECTURER);
    User user3 = new User("Heniek", "Sw", "51521521521", UserType.LECTURER);

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @MockBean
    private UserMapper userMapper;
    @MockBean
    private CourseMapper courseMapper;


    @Test
    void shouldReturnStatusOk() throws Exception {
        mockMvc.perform(get("/users/")).andDo(print()).andExpect(status().isOk());

        this.mockMvc.perform(get("/users/")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void shouldFindAllUsersAndReturnStatus200() throws Exception {
        when(userService.findAll()).thenReturn(List.of(user, user2, user3));

        mockMvc.perform(MockMvcRequestBuilders.get("/users/")).
                andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*]", hasSize(3)))
                .andExpect(status().is(200));
    }

    @Test
    void shouldFindUserByIdAndReturnStatus200() throws Exception {
        User user = new User("Dominika", "Swiokl", "92012703631", UserType.LECTURER);
        UserResponseDto userResponseDto = new UserResponseDto(1, "Dominika", "Swiokl", "92012703631", UserType.LECTURER, null);

        when(userMapper.mapUserToResponseDto(user)).thenReturn(userResponseDto);
        when(userService.findById(1)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Dominika"))
                .andExpect(jsonPath("$.personalNumber").value("92012703631"))
                .andExpect(status().is(200));
    }

    @Test
    void shouldReturnStatusCreated() throws Exception {
        String userMapper = new ObjectMapper().writeValueAsString(user);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users/")
                        .content(userMapper)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldDeleteUserById() throws Exception {
        mockMvc.perform(delete("/users/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted());
    }
}