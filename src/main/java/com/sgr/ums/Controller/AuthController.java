package com.sgr.ums.Controller;

import com.sgr.ums.Entity.User;
import com.sgr.ums.RequestModel.LoginUserRequest;
import com.sgr.ums.ResponseModel.ApiResponse;
import com.sgr.ums.Services.UserService.UserService;
import com.sgr.ums.Utilities.JsonUtils;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    ResponseEntity<ApiResponse<User>> auth(@Valid @RequestBody LoginUserRequest request){
        log.info("Login request started: {}", JsonUtils.toJson(request));
        return ResponseEntity.ok(userService.auth(request));
    }
}
