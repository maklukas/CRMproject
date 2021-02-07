package com.project.crm.controller;

import com.project.crm.domain.Dto.StatusDto;
import com.project.crm.mapper.StatusMapper;
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
    private StatusService service;

    @Autowired
    private StatusMapper mapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createStatus(@RequestBody StatusDto departmentDto) {
        service.createStatus(mapper.mapToStatus(departmentDto));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateStatus(@RequestBody StatusDto departmentDto) {
        service.updateStatus(mapper.mapToStatus(departmentDto));
    }

    @DeleteMapping(path = "/{id}")
    public void deleteStatus(@PathVariable int id) {
        service.deleteStatus(id);
    }

    @GetMapping
    public List<StatusDto> getStatuses() {
        return mapper.mapToStatusDtoList(service.getStatuses());
    }

    @GetMapping(path = "/{id}")
    public StatusDto getStatus(@PathVariable int id) {
        return mapper.mapToStatusDto(service.getStatus(id));
    }

}
