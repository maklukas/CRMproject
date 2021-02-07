package com.project.crm.domain.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StatusDto {
    private int id;
    private String name;
    private List<TaskDto> tasks;
    private List<InvestmentDto> investments;

}
