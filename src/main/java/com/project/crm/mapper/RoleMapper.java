package com.project.crm.mapper;

import com.project.crm.domain.Dto.RoleDto;
import com.project.crm.domain.Role;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleMapper {

    @Autowired
    private ModelMapper mapper;

    public Role mapToRole(RoleDto role) {
        return mapper.map(role, Role.class);
    }

    public RoleDto mapToRoleDto(Role role) {
        return mapper.map(role, RoleDto.class);
    }

    public List<Role> mapToRoleList(List<RoleDto> roles) {
        return roles.stream()
                .map(role -> mapper.map(role, Role.class))
                .collect(Collectors.toList());
    }

    public List<RoleDto> mapToRoleDtoList(List<Role> roles) {
        return roles.stream()
                .map(role -> mapper.map(role, RoleDto.class))
                .collect(Collectors.toList());
    }
}
