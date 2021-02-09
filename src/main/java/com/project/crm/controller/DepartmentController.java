package com.project.crm.controller;

import com.project.crm.domain.Dto.DepartmentDto;
import com.project.crm.mapper.DepartmentMapper;
import com.project.crm.mapper.MapperConnected;
import com.project.crm.service.DepartmentService;
import com.project.crm.service.ServiceConnected;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/departments")
@CrossOrigin("*")
public class DepartmentController {

    @Autowired
    private ServiceConnected service;

    @Autowired
    private MapperConnected mapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createDepartment(@RequestBody DepartmentDto departmentDto) {
        service.department.createDepartment(mapper.department.mapToDepartment(departmentDto));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateDepartment(@RequestBody DepartmentDto departmentDto) {
        service.department.updateDepartment(mapper.department.mapToDepartment(departmentDto));
    }

    @DeleteMapping(path = "/{id}")
    public void deleteDepartment(@PathVariable int id) {
        service.department.deleteDepartment(id);
    }

    @GetMapping
    public List<DepartmentDto> getDepartments() {
        return mapper.department.mapToDepartmentDtoList(service.department.getDepartments());
    }

    @GetMapping(path = "/{id}")
    public DepartmentDto getDepartment(@PathVariable int id) {
        return mapper.department.mapToDepartmentDto(service.department.getDepartment(id));
    }
}
