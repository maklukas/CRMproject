package com.project.crm.mapper;

import com.project.crm.domain.Status;
import com.project.crm.domain.StatusDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StatusMapper {
    public Status mapToStatus(StatusDto statusDto) {
        return new Status(
                statusDto.getId(),
                statusDto.getName()
        );
    }
    public StatusDto mapToStatusDto(Status status) {
        return new StatusDto(
                status.getId(),
                status.getName()
        );
    }

    public List<StatusDto> mapToStatusDtoList(List<Status> statusList) {
        return statusList.stream()
                .map(status -> new StatusDto(
                        status.getId(),
                        status.getName()
                ))
                .collect(Collectors.toList());
    }
}
