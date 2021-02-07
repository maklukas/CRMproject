package com.project.crm.mapper;

import com.project.crm.domain.Department;
import com.project.crm.domain.Dto.DepartmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DepartmentMapper {

    @Autowired
    private UserMapper userMapper;

    public Department mapToDepartment(DepartmentDto department) {
        return new Department(
                department.getId(),
                department.getName(),
                userMapper.mapToUserList(department.getUsers())
        );
    }

    public DepartmentDto mapToDepartmentDto(Department department) {
        return new DepartmentDto(
                department.getId(),
                department.getName(),
                userMapper.mapToUserDtoList(department.getUsers())
        );
    }

    public List<DepartmentDto> mapToDepartmentDtoList(List<Department> departments) {
        return departments.stream()
                .map(department -> new DepartmentDto(
                        department.getId(),
                        department.getName(),
                        userMapper.mapToUserDtoList(department.getUsers())
                )).collect(Collectors.toList());
    }

    public List<Department> mapToDepartmentList(List<DepartmentDto> departments) {
        return departments.stream()
                .map(department -> new Department(
                        department.getId(),
                        department.getName(),
                        userMapper.mapToUserList(department.getUsers())
                )).collect(Collectors.toList());
    }

}
