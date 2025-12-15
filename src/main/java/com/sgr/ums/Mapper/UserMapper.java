package com.sgr.ums.Mapper;

import com.sgr.ums.Entity.User;
import com.sgr.ums.RequestModel.UserRequestModel.AddUserRequest;
import com.sgr.ums.RequestModel.UserRequestModel.UpdateUserRequest;

public class UserMapper {

    public static User Adduser (AddUserRequest request){

        User user= new User();
        user.setUserName(request.getEmail());
        user.setEmail( request.getEmail());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setMiddleName(request.getMiddleName());
        user.setLastName(request.getLastName());
        user.setNativeName(request.getNativeName());
        user.setAge(request.getAge());
        user.setAddress(request.getAddress());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setCountry(request.getCountry());
        return user;
    }


    public static User Updateuser (User user, UpdateUserRequest request){

        user.setFirstName(request.getFirstName());
        user.setMiddleName(request.getMiddleName());
        user.setLastName(request.getLastName());
        user.setNativeName(request.getNativeName());
        user.setAge(request.getAge());
        user.setAddress(request.getAddress());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setCountry(request.getCountry());
        return user;
    }
}
