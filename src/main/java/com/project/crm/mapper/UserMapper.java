package com.project.crm.mapper;

import com.project.crm.domain.User;
import com.project.crm.domain.Dto.UserDto;
import com.project.crm.securingweb.PassEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    private static Logger LOGGER = LoggerFactory.getLogger(UserMapper.class);

    @Autowired
    private MapperConnected mapper;

    public User mapToUser(UserDto user) {
        if (user == null) {
            LOGGER.error("Mapper filed..");
            return new User();
        } else {
            return new User(
                    user.getId(),
                    user.getUsername(),
                    new PassEncryptor().passwordEncoder().encode("password"),
                    user.getFirstname(),
                    user.getLastname(),
                    mapper.department.mapToDepartment(user.getDepartment()),
                    mapper.investment.mapToInvestmentList(user.getInvestments()),
                    mapper.task.mapToTaskList(user.getTasks())
            );
        }
    }

    public UserDto mapToUserDto(User user) {
        if (user == null) {
            LOGGER.error("Mapper filed..");
            return new UserDto();
        } else {
            return new UserDto(
                    user.getId(),
                    user.getUsername(),
                    user.getFirstname(),
                    user.getLastname(),
                    mapper.department.mapToDepartmentDto(user.getDepartment()),
                    mapper.investment.mapToInvestmentDtoList(user.getInvestments()),
                    mapper.task.mapToTaskDtoList(user.getTasks())
            );
        }
    }

    public List<UserDto> mapToUserDtoList(List<User> users) {
        if (users == null) {
            LOGGER.error("Mapper filed..");
            return new ArrayList<>();
        } else {
            return users.stream()
                    .map(user -> new UserDto(
                            user.getId(),
                            user.getUsername(),
                            user.getFirstname(),
                            user.getLastname(),
                            mapper.department.mapToDepartmentDto(user.getDepartment()),
                            mapper.investment.mapToInvestmentDtoList(user.getInvestments()),
                            mapper.task.mapToTaskDtoList(user.getTasks())
                    )).collect(Collectors.toList());
        }
    }

    public List<User> mapToUserList(List<UserDto> users) {
        if (users == null) {
            LOGGER.error("Mapper filed..");
            return new ArrayList<>();
        } else {
            return users.stream()
                    .map(user -> new User(
                            user.getId(),
                            user.getUsername(),
                            new PassEncryptor().passwordEncoder().encode("password"),
                            user.getFirstname(),
                            user.getLastname(),
                            mapper.department.mapToDepartment(user.getDepartment()),
                            mapper.investment.mapToInvestmentList(user.getInvestments()),
                            mapper.task.mapToTaskList(user.getTasks())
                    )).collect(Collectors.toList());
        }
    }

}
