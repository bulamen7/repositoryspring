package com.bulamen7.learningapp.mapper;

import com.bulamen7.learningapp.model.User;
import com.bulamen7.learningapp.model.dto.response.UserResponseDto;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public UserResponseDto mapUserToDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setLastName(user.getLastName());
        dto.setPersonalNumber(user.getPersonalNumber());
        dto.setType(user.getType());
        return dto;
    }

    public User mapDtoToUser(UserResponseDto userResponseDto) {
        User user = new User();
        user.setId(userResponseDto.getId());
        user.setName(userResponseDto.getName());
        user.setLastName(userResponseDto.getLastName());
        user.setPersonalNumber(userResponseDto.getPersonalNumber());
        user.setType(userResponseDto.getType());
        return user;
    }
}
