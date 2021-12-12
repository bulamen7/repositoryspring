package com.bulamen7.learningapp.repository;

import com.bulamen7.learningapp.mapper.UserMapper;
import com.bulamen7.learningapp.model.UserType;
import com.bulamen7.learningapp.model.dto.request.UserRequestDto;
import com.bulamen7.learningapp.model.dto.response.UserResponseDto;
import com.bulamen7.learningapp.service.UserService;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {
    @MockBean
    UserService userService;
    @MockBean
    UserRepository userRepository;
    @MockBean
    UserMapper userMapper;

    @Test
    void shouldFindAllUsers() {
        //given
        UserResponseDto user = new UserResponseDto(1, "Marek", "Swiok", "92012703631", UserType.LECTURER, null);
        UserResponseDto user2 = new UserResponseDto(2, "Jarekarek", "Swiok", "92012703631", UserType.LECTURER, null);
        List<UserResponseDto> users = List.of(user, user2);
        //when
        when(userService.findAll()).thenReturn(List.of(user, user2));
        //then
        assertEquals(userService.findAll(), users);
    }

    @Test
    void shouldNotFindAll() {
        //given
        UserResponseDto user = new UserResponseDto(1, "Marek", "Swiok", "92012703631", UserType.LECTURER, null);
        UserResponseDto user2 = new UserResponseDto(2, "Jarekarek", "Swiok", "92012703631", UserType.LECTURER, null);
        List<UserResponseDto> users = List.of(user, user2);
        //when
        when(userService.findAll()).thenReturn(List.of(user));
        //then
        assertNotEquals(userService.findAll(), users);
    }

    @Test
    void shouldFindUserById() throws NotFoundException {
        //given
        UserResponseDto user = new UserResponseDto(1, "Marek", "Swiok", "92012703631", UserType.LECTURER, null);
        //when
        when(userService.findById(1)).thenReturn(Optional.of(new UserResponseDto(1, "Marek", "Swiok", "92012703631", UserType.LECTURER, null)));
        //then
        assertEquals(userService.findById(1).get(), user);
    }

    @Test
    void shouldSaveUserWhenNotExist() {

        //given
        UserRequestDto user = new UserRequestDto("Marek", "Swiok", "92012703631", UserType.LECTURER);
        given(userRepository.findById(any())).willReturn(Optional.ofNullable(null));
        //when
        //then
        userService.saveUser(user);
    }


    @Test
    void shouldDeleteUserById() throws RuntimeException {
        //given
        UserRequestDto user = new UserRequestDto("Marek", "Swiok", "92012703631", UserType.LECTURER);
        given(userRepository.findById(any())).willReturn(Optional.ofNullable(null));

        //when

        //then
        userService.deleteUserById(user.getId());
    }

}

//    @Test
//    void shouldThrowExceptionWhenSavingDuplicatedUser() {
//        //given
//        userRepository.deleteAll();
//        userService.saveUser(user);
//        //when
//
//        //then
//        Assertions.assertThatThrownBy(() -> userService.saveUser(user)).isInstanceOf(IllegalStateException.class).hasMessage("Duplicated User");
//    }
//
//
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
















