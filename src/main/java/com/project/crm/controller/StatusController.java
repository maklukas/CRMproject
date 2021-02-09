package com.project.crm.controller;

import com.project.crm.domain.Dto.StatusDto;
import com.project.crm.mapper.MapperConnected;
import com.project.crm.mapper.StatusMapper;
import com.project.crm.service.ServiceConnected;
import com.project.crm.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/statuses")
@CrossOrigin("*")
public class StatusController {

    @Autowired
    private ServiceConnected service;

    @Autowired
    private MapperConnected mapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createStatus(@RequestBody StatusDto departmentDto) {
        service.status.createStatus(mapper.status.mapToStatus(departmentDto));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateStatus(@RequestBody StatusDto departmentDto) {
        service.status.updateStatus(mapper.status.mapToStatus(departmentDto));
    }

    @DeleteMapping(path = "/{id}")
    public void deleteStatus(@PathVariable int id) {
        service.status.deleteStatus(id);
    }

    @GetMapping
    public List<StatusDto> getStatuses() {
        return mapper.status.mapToStatusDtoList(service.status.getStatuses());
    }

    @GetMapping(path = "/{id}")
    public StatusDto getStatus(@PathVariable int id) {
        return mapper.status.mapToStatusDto(service.status.getStatus(id));
    }

}
