package com.bulamen7.learningapp.mapper;

import com.bulamen7.learningapp.model.User;
import com.bulamen7.learningapp.model.dto.request.UserRequestDto;
import com.bulamen7.learningapp.model.dto.response.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserRequestDto mapUserToRequestDto(User user) {
        UserRequestDto dto = new UserRequestDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setLastName(user.getLastName());
        dto.setPersonalNumber(user.getPersonalNumber());
        dto.setType(user.getType());
        return dto;
    }
    public User mapRequestDtoToUser(UserRequestDto userRequestDto) {
        User user = new User();
        user.setId(userRequestDto.getId());
        user.setName(userRequestDto.getName());
        user.setLastName(userRequestDto.getLastName());
        user.setPersonalNumber(userRequestDto.getPersonalNumber());
        user.setType(userRequestDto.getType());
        return user;
    }
    public UserResponseDto mapUserToResponseDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setLastName(user.getLastName());
        dto.setType(user.getType());
        return dto;
    }

    public User mapResponseDtoToUser(UserResponseDto userResponseDto) {
        User user = new User();
        user.setId(userResponseDto.getId());
        user.setName(userResponseDto.getName());
        user.setLastName(userResponseDto.getLastName());
        user.setType(userResponseDto.getType());
        return user;
    }
}
