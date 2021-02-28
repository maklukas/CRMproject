package com.project.crm.domain.Dto;

import com.project.crm.domain.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private int id;
    private String username;
    private String firstname;
    private String lastname;
    private Department department;
}
