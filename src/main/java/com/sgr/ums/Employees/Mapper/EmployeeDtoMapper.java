package com.sgr.ums.Employees.Mapper;

import com.sgr.ums.Employees.Model.Employee;
import com.sgr.ums.Employees.Model.EmployeeDTO;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeDtoMapper {
    public static EmployeeDTO toDto(Employee entity) {

        if (entity == null) return null;

        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(entity.getId());
        dto.setUuid(entity.getUuid());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setDepartment(entity.getDepartment());
        dto.setAge(entity.getAge());
        dto.setStatus(entity.getStatus().name());
        dto.setIsActive(entity.getIsActive());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUpdatedBy(entity.getUpdatedBy());
        dto.setUpdatedDate(entity.getUpdatedDate());

        return dto;
    }

    public static List<EmployeeDTO> toDtoList(List<Employee> entities) {
        return entities.stream()
                .map(EmployeeDtoMapper::toDto)
                .collect(Collectors.toList());
    }
}
