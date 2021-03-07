package com.project.crm.mapper;

import com.project.crm.domain.Department;
import com.project.crm.domain.Dto.DepartmentDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DepartmentMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Department mapToDepartment(DepartmentDto department) {
        return modelMapper.map(department, Department.class);
    }

    public DepartmentDto mapToDepartmentDto(Department department) {
        return modelMapper.map(department, DepartmentDto.class);
    }

    public List<Department> mapToDepartmentList(List<DepartmentDto> departments) {
        return departments.stream()
                .map(department -> modelMapper.map(department, Department.class))
                .collect(Collectors.toList());
    }

    public List<DepartmentDto> mapToDepartmentDtoList(List<Department> departments) {
        return departments.stream()
                .map(department -> modelMapper.map(department, DepartmentDto.class))
                .collect(Collectors.toList());
    }

}
