package com.bulamen7.learningapp.repository;

import com.bulamen7.learningapp.model.User;
import com.bulamen7.learningapp.model.UserType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserRepositoryTest {
    UserRepository userRepository = new UserRepository();

    @Test
    void savingUserShouldAddUserToDb() {
        //given
        User user = new User(5, "Arek", "Sw", "51521521521", UserType.TEACHER);
        //when
        userRepository.saveUser(user);
        //then
        assertEquals(1,userRepository.getDb().size());
    }

    @Test
    void findingUserByIdshouldFindUserWithGivenId() {
        //given
        User user = new User(1, "Dominika", "Sw", "51521521521", UserType.TEACHER);
        User user2 = new User(2, "Marek", "Sw", "51521521", UserType.TEACHER);
        User user3 = new User(5, "Arek", "Sw", "51521521", UserType.TEACHER);

        //when
        userRepository.saveUser(user2);
        userRepository.saveUser(user);
        userRepository.saveUser(user3);
        User expectedUser = userRepository.findById(2);

        //then
        assertEquals(expectedUser, user2);
    }

    @Test
    void findingAllUsersShouldFindAllUsers() {
        //given
        User user = new User(1, "Dominika", "Sw", "51521521521", UserType.TEACHER);
        User user2 = new User(2, "Marek", "Sw", "51521521", UserType.TEACHER);
        User user3 = new User(5, "Arek", "Sw", "51521521", UserType.TEACHER);
        List<User> users = List.of(user, user2, user3);
        //when
        userRepository.saveUser(user);
        userRepository.saveUser(user2);
        userRepository.saveUser(user3);
        List<User> expected = userRepository.findAll();

        //then
        assertEquals(expected, users);
    }

    @Test
    void deleteUserByIdshouldDeleteUserWithGivenId() {
        //given
        User user = new User(1, "Dominika", "Sw", "51521521521", UserType.TEACHER);
        User user2 = new User(2, "Marek", "Sw", "51521521", UserType.TEACHER);
        User user3 = new User(5, "Arek", "Sw", "51521521", UserType.TEACHER);
        //when
        userRepository.saveUser(user);
        userRepository.saveUser(user2);
        userRepository.saveUser(user3);
        userRepository.deleteUserById(1);
        //then
        assertEquals(2, userRepository.getDb().size());
    }

}
































