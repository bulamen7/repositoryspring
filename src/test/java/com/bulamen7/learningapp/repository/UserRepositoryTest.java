package com.bulamen7.learningapp.repository;

import com.bulamen7.learningapp.model.User;
import com.bulamen7.learningapp.model.UserType;
import com.bulamen7.learningapp.service.UserService;
import javassist.NotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
class UserRepositoryTest {
    //POJEDYNCZO PRZECHODZA WSZYSTKIE, NA RAZ TYLKO 1

    @Autowired
    UserService userService;

    User user = new User(1, "Marek", "Swiok", "90012703631", UserType.LECTURER);
    User user2 = new User(2, "Marek", "Swiok", "90012703631", UserType.LECTURER);
    User user3 = new User(3, "Arek", "Swiok", "82012703631", UserType.LECTURER);

    @Test
    void shouldSaveUserWhenNotExist() {
        //given
        createUser(user,user2,user3);

        //when
        userService.saveUser(user);
        userService.saveUser(user2);
        userService.saveUser(user3);
        //then
        assertEquals(3, userService.findAll().size());
    }

    @Test
    void shouldFindUserById() throws NotFoundException {
        //given
        createUser(user,user2,user3);

        userService.saveUser(user2);
        //when
        User expectedUser = userService.findById(2);

        //then
        assertEquals(expectedUser, user2);
    }

    @Test
    void shouldFindAllUsers() {
        //given
        createUser(user,user2,user3);

        List<User> users = List.of(user, user2, user3);
        userService.saveUser(user);
        userService.saveUser(user2);
        userService.saveUser(user3);
        //when
        List<User> expected = userService.findAll();

        //then
        assertEquals(expected, users);
    }

    @Test
    void shouldDeleteUserById() {
        //given
        createUser(user,user2,user3);

        userService.saveUser(user);
        userService.saveUser(user2);
        userService.saveUser(user3);
        //when
        userService.deleteUserById(1);

        //then
        assertEquals(3, userService.findAll().size());
    }

    @Test
    void shouldThrowExceptionWhenSavingDuplicatedUser() {
        //given
        createUser(user,user2,user3);

        userService.saveUser(user);
        //when

        //then
        Assertions.assertThatThrownBy(() -> userService.saveUser(user)).isInstanceOf(IllegalStateException.class).hasMessage("Duplicated User");
    }


    private User createUser(User... users) {
        return user;
    }

}
































