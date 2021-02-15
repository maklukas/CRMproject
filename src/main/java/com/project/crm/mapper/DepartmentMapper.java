package com.project.crm.mapper;

import com.project.crm.domain.Department;
import com.project.crm.domain.Dto.DepartmentDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DepartmentMapper {

    private static Logger LOGGER = LoggerFactory.getLogger(DepartmentMapper.class);

    @Autowired
    private MapperConnected mapper;

    public Department mapToDepartment(DepartmentDto department) {
        if (department == null) {
            LOGGER.error("Mapping filed..");
            return new Department();
        } else {
            return new Department(
                    department.getId(),
                    department.getName(),
                    mapper.user.mapToUserList(department.getUsers())
            );
        }
    }

    public DepartmentDto mapToDepartmentDto(Department department) {
        if (department == null) {
            LOGGER.error("Mapping filed..");
            return new DepartmentDto();
        } else {
            return new DepartmentDto(
                    department.getId(),
                    department.getName(),
                    mapper.user.mapToUserDtoList(department.getUsers())
            );
        }
    }

    public List<DepartmentDto> mapToDepartmentDtoList(List<Department> departments) {
        if (departments == null) {
            LOGGER.error("Mapping filed..");
            return new ArrayList<>();
        } else {
            return departments.stream()
                    .map(department -> new DepartmentDto(
                            department.getId(),
                            department.getName(),
                            mapper.user.mapToUserDtoList(department.getUsers())
                    )).collect(Collectors.toList());
        }
    }

    public List<Department> mapToDepartmentList(List<DepartmentDto> departments) {
        if (departments == null) {
            LOGGER.error("Mapping filed..");
            return new ArrayList<>();
        } else {
            return departments.stream()
                    .map(department -> new Department(
                            department.getId(),
                            department.getName(),
                            mapper.user.mapToUserList(department.getUsers())
                    )).collect(Collectors.toList());
        }
    }

}
