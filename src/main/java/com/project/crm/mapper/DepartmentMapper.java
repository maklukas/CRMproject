package com.project.crm.mapper;

import com.project.crm.domain.Department;
import com.project.crm.domain.DepartmentDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DepartmentMapper {
    public Department mapToDepartment(DepartmentDto departmentDto) {
        return new Department(
                departmentDto.getId(),
                departmentDto.getName()
        );
    }
    public DepartmentDto mapToDepartmentDto(Department department) {
        return new DepartmentDto(
                department.getId(),
                department.getName()
        );
    }

    public List<DepartmentDto> mapToDepartmentDtoList(List<Department> departmentList) {
        return departmentList.stream()
                .map(department -> new DepartmentDto(
                        department.getId(),
                        department.getName()
                ))
                .collect(Collectors.toList());
    }
}
