package com.project.crm.domain.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private int id;
    private String username;
    private String firstname;
    private String lastname;
    private DepartmentDto department;
    private RoleDto role;
    private StatusDto status;
}
