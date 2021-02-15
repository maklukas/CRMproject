package com.project.crm.mapper;

import com.project.crm.domain.User;
import com.project.crm.domain.Dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    @Autowired
    private ModelMapper modelMapper;

    public User mapToUser(UserDto user) {
        return modelMapper.map(user, User.class);
    }

    public UserDto mapToUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public List<User> mapToUserList(List<UserDto> users) {
        return users.stream()
                .map(user -> modelMapper.map(user, User.class))
                .collect(Collectors.toList());
    }

    public List<UserDto> mapToUserDtoList(List<User> users) {
        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }
}
