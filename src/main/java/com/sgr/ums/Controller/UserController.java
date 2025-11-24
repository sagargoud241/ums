package com.sgr.ums.Controller;

import com.sgr.ums.Entity.User;
import com.sgr.ums.RequestModel.AddUserRequest;
import com.sgr.ums.RequestModel.DeleteUserRequest;
import com.sgr.ums.RequestModel.LoginUserRequest;
import com.sgr.ums.RequestModel.UpdateUserRequest;
import com.sgr.ums.ResponseModel.ApiResponse;
import com.sgr.ums.Services.UserService;
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
}

