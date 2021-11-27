package com.bulamen7.learningapp.repository;

import com.bulamen7.learningapp.model.User;
import com.bulamen7.learningapp.model.UserType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserRepositoryTest {
    UserRepository userRepository = new UserRepository();
    User user = new User(1, "Dominika", "Sw", "51521521521", UserType.LECTURER);
    User user2 = new User(2, "Marek", "Sw", "51521521", UserType.LECTURER);
    User user3 = new User(5, "Arek", "Sw", "51521521", UserType.LECTURER);
    @Test
    void shouldSaveUserWhenNotExist() {
        //given
        createUser(user);

        //when
        userRepository.saveUser(user);
        //then
        assertEquals(1, userRepository.getIdToUser().size());
    }

    @Test
    void shouldFindUserById() {
        //given
        createUser(user,user2,user3);

        userRepository.saveUser(user);
        userRepository.saveUser(user2);
        userRepository.saveUser(user3);
        //when
        User expectedUser = userRepository.findById(2);

        //then
        assertEquals(expectedUser, user2);
    }

    @Test
    void shouldFindAllUsers() {
        //given
        createUser(user,user2,user3);
        List<User> users = List.of(user, user2, user3);
        userRepository.saveUser(user);
        userRepository.saveUser(user2);
        userRepository.saveUser(user3);
        //when

        List<User> expected = userRepository.findAll();

        //then
        assertEquals(expected, users);
    }

    @Test
    void shouldDeleteUserById() {
        //given
        createUser(user,user2,user3);
        userRepository.saveUser(user);
        userRepository.saveUser(user2);
        userRepository.saveUser(user3);

        //when
        userRepository.deleteUserById(1);

        //then
        assertEquals(2, userRepository.getIdToUser().size());
    }

    @Test
    void shouldThrowExceptionWhenSavingDuplicatedUser() {
        //given

        createUser(user,user2,user3);
        userRepository.saveUser(user);
        //when

        //then
        Assertions.assertThatThrownBy(() -> userRepository.saveUser(user)).isInstanceOf(IllegalStateException.class).hasMessage("Duplicated User");
    }


   private User createUser(User...users) {
        return user;
    }

}
































