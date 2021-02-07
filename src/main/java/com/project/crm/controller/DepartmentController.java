package com.project.crm.controller;

import com.project.crm.domain.Dto.DepartmentDto;
import com.project.crm.mapper.DepartmentMapper;
import com.project.crm.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/departments")
@CrossOrigin("*")
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @Autowired
    private DepartmentMapper mapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createDepartment(@RequestBody DepartmentDto departmentDto) {
        service.createDepartment(mapper.mapToDepartment(departmentDto));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateDepartment(@RequestBody DepartmentDto departmentDto) {
        service.updateDepartment(mapper.mapToDepartment(departmentDto));
    }

    @DeleteMapping(path = "/{id}")
    public void deleteDepartment(@PathVariable int id) {
        service.deleteDepartment(id);
    }

    @GetMapping
    public List<DepartmentDto> getDepartments() {
        return mapper.mapToDepartmentDtoList(service.getDepartments());
    }

    @GetMapping(path = "/{id}")
    public DepartmentDto getDepartment(@PathVariable int id) {
        return mapper.mapToDepartmentDto(service.getDepartment(id));
    }
}
