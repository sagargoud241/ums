package com.sgr.ums.Services;

import com.sgr.ums.Entity.User;
import com.sgr.ums.RequestModel.AddUserRequest;
import com.sgr.ums.RequestModel.DeleteUserRequest;
import com.sgr.ums.RequestModel.LoginUserRequest;
import com.sgr.ums.RequestModel.UpdateUserRequest;
import com.sgr.ums.ResponseModel.ApiResponse;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;

import java.util.UUID;

public interface UserService {
    ApiResponse<User> addUser(@Valid AddUserRequest request);
    ApiResponse<User>findByUuid(UUID uuid);
    ApiResponse<User>getUserById(Long id);
    ApiResponse<User> login(@Valid LoginUserRequest request);
    ApiResponse<User>updateUser(UpdateUserRequest request);
    ApiResponse<User>deleteUser(DeleteUserRequest request);

    ApiResponse<User> auth(@Valid LoginUserRequest request);
}

