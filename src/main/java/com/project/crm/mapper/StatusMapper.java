package com.project.crm.mapper;

import com.project.crm.domain.Status;
import com.project.crm.domain.Dto.StatusDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StatusMapper {

    private static Logger LOGGER = LoggerFactory.getLogger(StatusMapper.class);

    @Autowired
    private MapperConnected mapper;

    public Status mapToStatus(StatusDto status) {
        if (status == null) {
            LOGGER.error("Mapping filed..");
            return new Status();
        } else {
            return new Status(
                    status.getId(),
                    status.getName(),
                    mapper.task.mapToTaskList(status.getTasks()),
                    mapper.investment.mapToInvestmentList(status.getInvestments())
            );
        }
    }

    public StatusDto mapToStatusDto(Status status) {
        if (status == null) {
            LOGGER.error("Mapping filed..");
            return new StatusDto();
        } else {
            return new StatusDto(
                    status.getId(),
                    status.getName(),
                    mapper.task.mapToTaskDtoList(status.getTasks()),
                    mapper.investment.mapToInvestmentDtoList(status.getInvestments())
            );
        }
    }

    public List<StatusDto> mapToStatusDtoList(List<Status> statuses) {
        if (statuses == null) {
            LOGGER.error("Mapping filed..");
            return new ArrayList<>();
        } else {
            return statuses.stream()
                    .map(status -> new StatusDto(
                            status.getId(),
                            status.getName(),
                            mapper.task.mapToTaskDtoList(status.getTasks()),
                            mapper.investment.mapToInvestmentDtoList(status.getInvestments())
                    )).collect(Collectors.toList());
        }
    }

    public List<Status> mapToStatusList(List<StatusDto> statuses) {
        if (statuses == null) {
            LOGGER.error("Mapping filed..");
            return new ArrayList<>();
        } else {
            return statuses.stream()
                    .map(status -> new Status(
                            status.getId(),
                            status.getName(),
                            mapper.task.mapToTaskList(status.getTasks()),
                            mapper.investment.mapToInvestmentList(status.getInvestments())
                    )).collect(Collectors.toList());
        }
    }

}
