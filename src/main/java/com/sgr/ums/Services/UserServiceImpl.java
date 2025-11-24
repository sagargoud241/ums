package com.sgr.ums.Services;


import com.sgr.ums.Entity.User;
import com.sgr.ums.Mapper.UserMapper;
import com.sgr.ums.Repository.UserRepository;
import com.sgr.ums.RequestModel.AddUserRequest;
import com.sgr.ums.RequestModel.DeleteUserRequest;
import com.sgr.ums.RequestModel.LoginUserRequest;
import com.sgr.ums.RequestModel.UpdateUserRequest;
import com.sgr.ums.ResponseModel.ApiResponse;
import com.sgr.ums.Utilities.Utility;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public ApiResponse<User> addUser(AddUserRequest request) {
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


}









