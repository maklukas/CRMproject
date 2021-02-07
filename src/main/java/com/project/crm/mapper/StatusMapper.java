package com.project.crm.mapper;

import com.project.crm.domain.Status;
import com.project.crm.domain.Dto.StatusDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StatusMapper {

    @Autowired
    private TaskMapper taskMapper;
    private InvestmentMapper investmentMapper;

    public Status mapToStatus(StatusDto status) {
        return new Status(
                status.getId(),
                status.getName(),
                taskMapper.mapToTaskList(status.getTasks()),
                investmentMapper.mapToInvestmentList(status.getInvestments())
        );
    }

    public StatusDto mapToStatusDto(Status status) {
        return new StatusDto(
                status.getId(),
                status.getName(),
                taskMapper.mapToTaskDtoList(status.getTasks()),
                investmentMapper.mapToInvestmentDtoList(status.getInvestments())
        );
    }

    public List<StatusDto> mapToStatusDtoList(List<Status> statuses) {
        return statuses.stream()
                .map(status -> new StatusDto(
                        status.getId(),
                        status.getName(),
                        taskMapper.mapToTaskDtoList(status.getTasks()),
                        investmentMapper.mapToInvestmentDtoList(status.getInvestments())
                )).collect(Collectors.toList());
    }

    public List<Status> mapToStatusList(List<StatusDto> statuses) {
        return statuses.stream()
                .map(status -> new Status(
                        status.getId(),
                        status.getName(),
                        taskMapper.mapToTaskList(status.getTasks()),
                        investmentMapper.mapToInvestmentList(status.getInvestments())
                )).collect(Collectors.toList());
    }

}
