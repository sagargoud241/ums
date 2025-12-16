package com.sgr.ums.users.mappers;

import com.sgr.ums.Entity.User;
import com.sgr.ums.users.models.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

public class UserDtoMapper {

    public static UserDTO toDto(User entity) {
        if (entity == null) return null;

        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setUuid(entity.getUuid());
        dto.setUserName(entity.getUserName());
        dto.setEmail(entity.getEmail());
        dto.setFirstName(entity.getFirstName());
        dto.setMiddleName(entity.getMiddleName());
        dto.setLastName(entity.getLastName());
        dto.setFullName(entity.getFullName());
        dto.setNativeName(entity.getNativeName());
        dto.setAge(entity.getAge());
        dto.setAddress(entity.getAddress());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setCountry(entity.getCountry());
        dto.setRemarks(entity.getRemarks());
        dto.setDescription(entity.getDescription());
        dto.setIsActive(entity.isActive());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUpdatedBy(entity.getUpdatedBy());
        dto.setUpdatedDate(entity.getUpdatedDate());

        return dto;
    }
    public static List<UserDTO> toDtoList(List<User> users) {
        return users.stream()
                .map(UserDtoMapper::toDto)
                .collect(Collectors.toList());
    }

}

