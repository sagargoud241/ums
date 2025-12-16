package com.sgr.ums.Controller;

import com.sgr.ums.Employees.Model.EmployeeResponse;
import com.sgr.ums.Employees.config.EmployeesResponseMessages;
import com.sgr.ums.Entity.User;
import com.sgr.ums.RequestModel.UserRequestModel.AddUserRequest;
import com.sgr.ums.RequestModel.UserRequestModel.DeleteUserRequest;
import com.sgr.ums.RequestModel.LoginUserRequest;
import com.sgr.ums.RequestModel.UserRequestModel.UpdateUserRequest;
import com.sgr.ums.ResponseModel.ApiResponse;
import com.sgr.ums.Services.UserService.UserService;
import com.sgr.ums.apiservices.configs.ApiResponseCodes;
import com.sgr.ums.apiservices.configs.ApiResponseMessages;
import com.sgr.ums.apiservices.models.GenericApiResponse;
import com.sgr.ums.users.models.UserResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    ResponseEntity<ApiResponse<User>> addUser(@Valid @RequestBody AddUserRequest request) {
        return ResponseEntity.ok(userService.addUser(request));
    }

    @GetMapping("/api/user/uuid/{uuid}")
    ResponseEntity<ApiResponse<User>>findByUuid(@PathVariable UUID uuid){
        return ResponseEntity.ok(userService.findByUuid(uuid));
    }

    @GetMapping("/api/user/{id}")
    ResponseEntity<ApiResponse<User>>getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/login")
    ResponseEntity<ApiResponse<User>>login(@Valid  @RequestBody LoginUserRequest request){
        return ResponseEntity.ok(userService.login(request));
    }


    @PutMapping("/update")
    ResponseEntity<ApiResponse<User>>updateUser(@Valid @RequestBody UpdateUserRequest request){
        return ResponseEntity.ok(userService.updateUser(request));
    }
    @DeleteMapping("/delete")
    ResponseEntity<ApiResponse<User>>deleteUser(@Valid @RequestBody DeleteUserRequest request){
        return ResponseEntity.ok(userService.deleteUser(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericApiResponse> getById(@PathVariable Long id) {
        GenericApiResponse apiResponse = new GenericApiResponse();
        apiResponse.setStatus(ApiResponseCodes.FAILURE);
        apiResponse.setMessageCode(ApiResponseMessages.ENTITY_NOT_FOUND.name());
        try {
            UserResponse response = userService.findUserById(id);
            if (response != null && response.getMessage().equals(EmployeesResponseMessages.SUCCESS.toString())) {
                apiResponse.setUserResponse(response);
                apiResponse.setStatus(ApiResponseCodes.SUCCESS);
                apiResponse.setMessageCode(ApiResponseMessages.DATA_RETRIEVED_SUCCESSFULLY.name());
            } else {
                apiResponse.setStatus(ApiResponseCodes.FAILURE);
            }
        } catch (Exception e) {
            apiResponse.setStatus(ApiResponseCodes.FAILURE);
            apiResponse.setMessageCode(ApiResponseMessages.REQUEST_TERMINATED.name());
        }

        return ResponseEntity.ok(apiResponse);
    }
}

