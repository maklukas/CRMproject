package com.project.crm.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserDto {
    private int id;
    private String username;
    private String firstname;
    private String lastname;
    private Department department;
}
