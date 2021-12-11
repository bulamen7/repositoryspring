//package com.bulamen7.learningapp.repository;
//
//import com.bulamen7.learningapp.mapper.UserMapper;
//import com.bulamen7.learningapp.model.User;
//import com.bulamen7.learningapp.model.UserType;
//import com.bulamen7.learningapp.model.dto.request.UserRequestDto;
//import com.bulamen7.learningapp.model.dto.response.UserResponseDto;
//import com.bulamen7.learningapp.service.UserService;
//import javassist.NotFoundException;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//class UserServiceTest {
//    @Autowired
//    UserService userService;
//    @MockBean
//    UserRepository userRepository;
//    @MockBean
//    UserMapper userMapper;
//    User user = new User("Marek", "Swiok", "90012703631", UserType.LECTURER);
//    User user2 = new User("Marek", "Swiok", "90012703631", UserType.LECTURER);
//    User user3 = new User("Arek", "Swiok", "82012703631", UserType.LECTURER);
//
//    @Test
//    void shouldFindAllUsers() {
//        //given
//        given(userRepository.findAll()).willReturn(List.of(user, user2, user3));
//        List<User> users = List.of(user, user2, user3);
//        userService.saveUser(userMapper.mapUserToRequestDto(user));
//        userService.saveUser(userMapper.mapUserToRequestDto(user2));
//        userService.saveUser(userMapper.mapUserToRequestDto(user3));
//        //when
//
//
//        //then
//        assertEquals(userService.findAll(), users);
//    }
//
//    @Test
//    void shouldFindUserById() throws NotFoundException {
//        //given
//        UserService userService = mock(UserService.class);
//        UserResponseDto user = new UserResponseDto("Marek", "Swiok",UserType.LECTURER, null );
//        //when
//        when(userService.findById(1)).thenReturn();
//        UserResponseDto byId = userService.findById(user.getId()).get();
//        //then
//        assertEquals(byId, user);
//    }
//
//    @Test
//    void shouldSaveUserWhenNotExist() {
//
//        //given
//        UserService userService = new UserService(userRepository, mapper, courseMapper);
//        given(userService.saveUser(Mockito.any(User.class))).willReturn(new User("Marek", "Swiok", "90012703631", UserType.LECTURER));
//
//        //when
//        User user2 = userService.saveUser(new User("Marek", "Swiokasdsadsa", "90012703631", UserType.LECTURER));
//        //then
//        assertEquals(user2.getName(), "Marek");
//    }
//
//    @Test
//    void shouldDeleteUserById() {
//        //given
//        userRepository.deleteAll();
//        userService.saveUser(user);
//        userService.saveUser(user2);
//        userService.saveUser(user3);
//        //when
//        userService.deleteUserById(1);
//
//        //then
//        assertEquals(3, userService.findAll().size());
//    }
//
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
//
