package com.bulamen7.learningapp.service;

import com.bulamen7.learningapp.model.User;
import com.bulamen7.learningapp.model.UserType;
import com.bulamen7.learningapp.repository.CourseRepository;
import com.bulamen7.learningapp.repository.UserRepository;
import javassist.NotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {
    @MockBean
    UserRepository userRepository;
    @MockBean
    CourseRepository courseRepository;

    @Test
    void shouldFindAllUsers() {
        //given
        UserService userService = new UserService(userRepository, courseRepository);

        User user = new User("Marek", "Swiok", "92012703631", UserType.LECTURER);
        User user2 = new User("Jarekarek", "Swiok", "92012703631", UserType.LECTURER);
        List<User> users = List.of(user, user2);
        //when
        when(userService.findAll()).thenReturn(List.of(user, user2));
        //then
        assertThat(userService.findAll()).isEqualTo(users);
    }

    @Test
    void shouldFindAllAndNotBeEqual() {
        //given
        UserService userService = new UserService(userRepository, courseRepository);

        User user = new User("Marek", "Swiok", "92012703631", UserType.LECTURER);
        User user2 = new User("Jarekarek", "Swiok", "92012703631", UserType.LECTURER);
        List<User> users = List.of(user, user2);
        //when
        when(userService.findAll()).thenReturn(List.of(user));
        //then
        assertThat(userService.findAll()).isNotEqualTo(users);
    }


    @Test
    void shouldFindUserById() throws NotFoundException {
        //given
        UserService userService = new UserService(userRepository, courseRepository);
        User user = new User("Marek", "Swiok", "92012703631", UserType.LECTURER);
        //when
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        //then
        assertThat(userService.findById(1)).isEqualTo(user);

    }

    @Test
    void shouldSaveUserWhenNotExist() {

        //given
        UserService userService = new UserService(userRepository, courseRepository);
        User user = new User("Marek", "Swiok", "92012703631", UserType.LECTURER);

        //when
        when(userRepository.save(user)).thenReturn(user);
        User actualUser = userRepository.save(new User("Marek", "Swiok", "92012703631", UserType.LECTURER));
        //then
        assertThat(actualUser).isEqualTo(user);
        assertThat(actualUser.getName()).isEqualTo(user.getName());
        assertThat(actualUser.getPersonalNumber()).isEqualTo(user.getPersonalNumber());
    }

    @Test
    void shouldDeleteUserById() {
        //given
        UserService userService = new UserService(userRepository,courseRepository);
        userService.deleteUserById(5);
        verify(userRepository).deleteById(any());
    }

    @Test
    void shouldThrowExceptionWhenSavingDuplicatedUser() {
        //given
        UserService userService = mock(UserService.class);
        User user = new User("Marek", "Swiok", "92012703631", UserType.LECTURER);

        //then
        doThrow(new IllegalStateException("Duplicated User")).when(userService).saveUser(user);
        Assertions.assertThatThrownBy(() -> userService.saveUser(user)).isInstanceOf(IllegalStateException.class).hasMessage("Duplicated User");
    }
}











