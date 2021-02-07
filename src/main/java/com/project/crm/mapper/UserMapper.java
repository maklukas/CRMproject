package com.project.crm.mapper;

import com.project.crm.domain.User;
import com.project.crm.domain.Dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private InvestmentMapper investmentMapper;
    @Autowired
    private TaskMapper taskMapper;

    public User mapToUser(UserDto user) {
        return new User(
                user.getId(),
                user.getUsername(),
                null,
                user.getFirstname(),
                user.getLastname(),
                departmentMapper.mapToDepartment(user.getDepartment()),
                investmentMapper.mapToInvestmentList(user.getInvestments()),
                taskMapper.mapToTaskList(user.getTasks())
        );
    }

    public UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                departmentMapper.mapToDepartmentDto(user.getDepartment()),
                investmentMapper.mapToInvestmentDtoList(user.getInvestments()),
                taskMapper.mapToTaskDtoList(user.getTasks())
        );
    }

    public List<UserDto> mapToUserDtoList(List<User> users) {
        return users.stream()
                .map(user -> new UserDto(
                        user.getId(),
                        user.getUsername(),
                        user.getFirstname(),
                        user.getLastname(),
                        departmentMapper.mapToDepartmentDto(user.getDepartment()),
                        investmentMapper.mapToInvestmentDtoList(user.getInvestments()),
                        taskMapper.mapToTaskDtoList(user.getTasks())
                )).collect(Collectors.toList());
    }

    public List<User> mapToUserList(List<UserDto> users) {
        return users.stream()
                .map(user -> new User(
                        user.getId(),
                        user.getUsername(),
                        null,
                        user.getFirstname(),
                        user.getLastname(),
                        departmentMapper.mapToDepartment(user.getDepartment()),
                        investmentMapper.mapToInvestmentList(user.getInvestments()),
                        taskMapper.mapToTaskList(user.getTasks())
                )).collect(Collectors.toList());
    }

}
