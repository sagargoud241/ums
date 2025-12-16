package com.sgr.ums.users.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.List;
@Data
public class UserResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("message")
    String message;

    @Enumerated(EnumType.STRING)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("id")
    Long id;


    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("user")
    UserDTO user;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("users")
    List<UserDTO> users;


}