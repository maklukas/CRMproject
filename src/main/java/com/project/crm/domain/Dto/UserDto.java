package com.project.crm.domain.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserDto {
    private int id;
    private String username;
    private String firstname;
    private String lastname;
    private DepartmentDto department;
    private List<InvestmentDto> investments;
    private List<TaskDto> tasks;
}
