package com.sgr.ums.Services.UserService;


import com.sgr.ums.Authorization.JwtUtil;
import com.sgr.ums.Employees.Mapper.EmployeeDtoMapper;
import com.sgr.ums.Employees.Model.Employee;
import com.sgr.ums.Employees.Model.EmployeeResponse;
import com.sgr.ums.Employees.config.EmployeesResponseMessages;
import com.sgr.ums.Entity.User;
import com.sgr.ums.Mapper.UserMapper;
import com.sgr.ums.Repository.UserRepository;
import com.sgr.ums.RequestModel.UserRequestModel.AddUserRequest;
import com.sgr.ums.RequestModel.UserRequestModel.DeleteUserRequest;
import com.sgr.ums.RequestModel.LoginUserRequest;
import com.sgr.ums.RequestModel.UserRequestModel.UpdateUserRequest;
import com.sgr.ums.ResponseModel.ApiResponse;
import com.sgr.ums.Utilities.JsonUtils;
import com.sgr.ums.Utilities.Utility;
import com.sgr.ums.users.mappers.UserDtoMapper;
import com.sgr.ums.users.models.UserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private JwtUtil jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserServiceImpl(UserRepository userRepository, JwtUtil jwtUtil){
        this.userRepository=userRepository;
        this.jwtUtil=jwtUtil;
    }

    @Override
    public ApiResponse<User> addUser(AddUserRequest request) {
        log.info("AddUserRequest is  {}",JsonUtils.toJson(request));
        Optional<User> emailoptional = userRepository.findByEmail(request.getEmail());
        if (emailoptional!= null && !emailoptional.isEmpty()) {
            return ApiResponse.failure("Email already Used");
        }
        User user= UserMapper.Adduser(request);
        user.setCreatedBy(Utility.getDefaultUsername());
        user.setCreatedDate(LocalDateTime.now());
        user.setActive(true);
        user.setDeleted(false);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ApiResponse.success(user,"Add SuccessFully");

    }

    @Override
    public ApiResponse<User> findByUuid(UUID uuid) {
        Optional<User>optional=userRepository.findByUuid(uuid);
        if(optional.isEmpty()){
            return ApiResponse.failure("User not found");
        }
        return ApiResponse.success(optional.get(),"Fetch SuccessFully");
    }




    @Override
    public ApiResponse<User>getUserById(Long id) {
        Optional<User>optional=userRepository.findById(id);
        if(optional.isEmpty()){
            return ApiResponse.failure("User not Found");
        }
        return ApiResponse.success(optional.get(),"Fetch SuccessFully");
    }

    @Override
    public ApiResponse<User> login(LoginUserRequest request) {
        {

            Optional<User> optional = userRepository.findByUserNameAndIsActive(request.getUserName(), true);

            if (optional.isEmpty()) {
                return ApiResponse.failure("User not Found");
            }

            User user = optional.get();
            if (user.isDeleted()) {
                return ApiResponse.failure("User not found");
            }

            if (passwordEncoder.matches(request.getPassword(), optional.get().getPassword())) {
                return ApiResponse.success(optional.get(), "Fetch SuccessFully");
            } else {
                return ApiResponse.failure("User not Found");
            }
        }
    }

    @Override
    public ApiResponse<User> updateUser(UpdateUserRequest request) {
        Optional<User> optional = userRepository.findById(request.getId());
           if (optional.isEmpty()) {
                return ApiResponse.failure("User not found");
           }
           User user = optional.get();
           if (user.isDeleted()) {
                return ApiResponse.failure("User not found");
                   }
           user = UserMapper.Updateuser(user, request);
            //   user.UserMapper.updateUser(user,request);
            user.setUpdatedBy(Utility.getDefaultUsername());
           user.setUpdatedDate(LocalDateTime.now());
           userRepository.save(user);
           return ApiResponse.success(user, "Update Successfully");

    }

    @Override
    public ApiResponse<User> deleteUser(DeleteUserRequest request) {
        Optional<User> optional = userRepository.findById(request.getId());
           if (optional.isEmpty()) {
                return ApiResponse.failure("User Not found");
            }
           User user = optional.get();
            if (user.isDeleted()) {
               return ApiResponse.failure("User not found");
          }
           user.setActive(false);
            user.setDeleted(true);
            user.setRemarks(request.getRemarks());
            user.setUpdatedBy(Utility.getDefaultUsername());
            user.setUpdatedDate(LocalDateTime.now());
            userRepository.save(user);
            return ApiResponse.success(user, "Deleted Successfully");
        }

    @Override
    public ApiResponse<User> auth(LoginUserRequest request) {
        Optional<User> optional = userRepository.findByUserNameAndIsActive(request.getUserName(), true);

        if (optional.isEmpty()) {
            log.info("User not found");
            return ApiResponse.failure("User not Found");
        }

        User user = optional.get();
        if (user.isDeleted()) {
            log.info("User is deleted {}",request.getUserName());
            return ApiResponse.failure("User not found");
        }

        if (passwordEncoder.matches(request.getPassword(), optional.get().getPassword())) {
            log.info("User logged in success");
            String token = jwtUtil.createToken(user);
            log.info("User token is {}",token);
            return ApiResponse.success(optional.get(), "Bearer "+ token);
        } else {
            return ApiResponse.failure("User not Found");
        }
    }

    @Override
    public UserResponse findUserById(Long id) {
        log.info("Finding the User by id:{}", JsonUtils.toJson(id));
        UserResponse response = new UserResponse();
        Optional<User> optional = userRepository.findById(id);
        if (optional.isEmpty()) {
            log.info("User not find  by id");
            response.setMessage(EmployeesResponseMessages.NOT_AVAILABLE.toString());
            return response;
        }
        response.setUser(UserDtoMapper.toDto(optional.get()));
        response.setMessage(EmployeesResponseMessages.SUCCESS.toString());
        return response;
    }


}









