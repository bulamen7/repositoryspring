package com.bulamen7.learningapp.service;

import com.bulamen7.learningapp.model.User;
import com.bulamen7.learningapp.model.UserType;
import javassist.NotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {

    @Test
    void shouldFindAllUsers() {
        //given
        UserService userService = mock(UserService.class);

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
        UserService userService = mock(UserService.class);
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
        UserService userService = mock(UserService.class);
        User user = new User("Marek", "Swiok", "92012703631", UserType.LECTURER);
        when(userService.findById(1)).thenReturn(new User( "Marek", "Swiok", "92012703631", UserType.LECTURER));
        //then
        assertThat(userService.findById(1)).isEqualTo(user);

    }

    @Test
    void shouldSaveUserWhenNotExist() {

        //given
        UserService userService = mock(UserService.class);
        User user = new User("Marek", "Swiok", "92012703631", UserType.LECTURER);

        //when
        userService.saveUser(user);
        //then
        verify(userService).saveUser(user);
    }

    @Test
    void shouldDeleteUserById() throws RuntimeException {
        //given
        UserService userService = mock(UserService.class);
        User user = new User("Marek", "Swiok", "92012703631", UserType.LECTURER);
        userService.deleteUserById(user.getId());        //when

        //then
        verify(userService).deleteUserById(user.getId());
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











