package com.bulamen7.learningapp.repository;

import com.bulamen7.learningapp.model.UserType;
import com.bulamen7.learningapp.model.dto.request.UserRequestDto;
import com.bulamen7.learningapp.model.dto.response.UserResponseDto;
import com.bulamen7.learningapp.service.UserService;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

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

        UserResponseDto user = new UserResponseDto(1, "Marek", "Swiok", "92012703631", UserType.LECTURER, null);
        UserResponseDto user2 = new UserResponseDto(2, "Jarekarek", "Swiok", "92012703631", UserType.LECTURER, null);
        List<UserResponseDto> users = List.of(user, user2);
        //when
        when(userService.findAll()).thenReturn(List.of(user, user2));
        //then
        assertThat(userService.findAll()).isEqualTo(users);
    }

    @Test
    void shouldFindAllAndNotBeEqual() {
        //given
        UserService userService = mock(UserService.class);
        UserResponseDto user = new UserResponseDto(1, "Marek", "Swiok", "92012703631", UserType.LECTURER, null);
        UserResponseDto user2 = new UserResponseDto(2, "Jarekarek", "Swiok", "92012703631", UserType.LECTURER, null);
        List<UserResponseDto> users = List.of(user, user2);
        //when
        when(userService.findAll()).thenReturn(List.of(user));
        //then
        assertThat(userService.findAll()).isNotEqualTo(users);
    }


    @Test
    void shouldFindUserById() throws NotFoundException {
        //given
        UserService userService = mock(UserService.class);
        UserResponseDto user = new UserResponseDto(1, "Marek", "Swiok", "92012703631", UserType.LECTURER, null);
        //when
        when(userService.findById(1)).thenReturn(Optional.of(new UserResponseDto(1, "Marek", "Swiok", "92012703631", UserType.LECTURER, null)));
        //then
        assertThat(userService.findById(1)).get().isEqualTo(user);

    }

    @Test
    void shouldSaveUserWhenNotExist() {

        //given
        UserService userService = mock(UserService.class);
        UserRequestDto user = new UserRequestDto("Marek", "Swiok", "92012703631", UserType.LECTURER);
        //when
        userService.saveUser(user);
        //then
        verify(userService).saveUser(user);
    }

    @Test
    void shouldDeleteUserById() throws RuntimeException {
        //given
        UserService userService = mock(UserService.class);
        UserRequestDto user = new UserRequestDto("Marek", "Swiok", "92012703631", UserType.LECTURER);
        userService.deleteUserById(user.getId());        //when

        //then
        verify(userService).deleteUserById(user.getId());
    }

  @Test
  void shouldThrowExceptionWhenSavingDuplicatedUser() {
      //given
      UserService userService = mock(UserService.class);
      UserRequestDto user = new UserRequestDto("Marek", "Swiok", "92012703631", UserType.LECTURER);

      //then
      doThrow(new IllegalStateException("Duplicated User")).when(userService).saveUser(user);
  }
}











