package com.project.crm.mapper;

import com.project.crm.domain.Status;
import com.project.crm.domain.Dto.StatusDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StatusMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Status mapToStatus(StatusDto status) {
        return modelMapper.map(status, Status.class);
    }

    public StatusDto mapToStatusDto(Status status) {
        return modelMapper.map(status, StatusDto.class);
    }

    public List<Status> mapToStatusList(List<StatusDto> statuses) {
       return statuses.stream()
               .map(status -> modelMapper.map(status, Status.class))
               .collect(Collectors.toList());
    }

    public List<StatusDto> mapToStatusDtoList(List<Status> statuses) {
       return statuses.stream()
               .map(status -> modelMapper.map(status, StatusDto.class))
               .collect(Collectors.toList());
    }

}
