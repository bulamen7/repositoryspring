package com.bulamen7.learningapp.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class HelloWorldControllerTest {
    @Autowired
    UserController helloWorldController;

    @Test
    void shouldCheckAnswer() throws Exception {
        //given
        String name = "Ala";

        //when
        String answer = helloWorldController.helloWorld(name);

        //then
        assertThat(answer).isEqualTo("Hello Ala");



//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    void testHomePage() throws Exception{
//        mockMvc.perform(get("/hello-world")).andExpect(status().isOk());
//    }
    }
}