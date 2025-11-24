package com.sgr.ums.RequestModel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AddUserRequest {

    @NotBlank(message = "Password is required")

    private String password;

    @NotBlank(message = "FirstName is required")

    private String firstName;

    private String middleName;

    @NotBlank(message = "LastName is required")
    private String lastName;

    @NotBlank(message = "NativeName is required")
    private String nativeName;

    @NotNull(message = "Age is required")

    private Integer age;

    @NotBlank(message="Address is required")
    private String address;

    @NotBlank(message = "PhoneNumber is required")
    private String phoneNumber;

    @NotBlank(message = "Country is required")
    private String country;


    @NotBlank(message = "Email is required")
    private String email;



    //getter and setter
    public @NotBlank(message = "Address is required") String getAddress() {
        return address;
    }

    public void setAddress(@NotBlank(message = "Address is required") String address) {
        this.address = address;
    }

    public @NotNull(message = "Age is required") Integer getAge() {
        return age;
    }

    public void setAge(@NotBlank(message = "Age is required") Integer age) {
        this.age = age;
    }

    public @NotBlank(message = "Country is required") String getCountry() {
        return country;
    }

    public void setCountry(@NotBlank(message = "Country is required") String country) {
        this.country = country;
    }

    public @NotBlank(message = "Email is required") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email is required") String email) {
        this.email = email;
    }

    public @NotBlank(message = "FirstName is required") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotBlank(message = "FirstName is required") String firstName) {this.firstName = firstName;}

    public @NotBlank(message = "LastName is required") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotBlank(message = "LastName is required") String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public @NotBlank(message = "NativeName is required") String getNativeName() {
        return nativeName;
    }

    public void setNativeName(@NotBlank(message = "NativeName is required") String nativeName) {this.nativeName = nativeName;}

    public @NotBlank(message = "Password is required") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password is required") String password) {
        this.password = password;
    }

    public @NotBlank(message = "PhoneNumber is required") String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NotBlank(message = "PhoneNumber is required") String phoneNumber) {this.phoneNumber = phoneNumber;}








    public AddUserRequest (){}
    public AddUserRequest(String email,String password,String firstName,String middleName,String lastName,String nativeName,Integer age,String address,String phoneNumber,String country) {

        // this.userName = userName;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nativeName = nativeName;
        this.age = age;
        this.address=address;
        this.phoneNumber = phoneNumber;
        this.country = country;


    }


    @Override
    public String toString(){
        return "AddUserRequest'("+
                // ",userName='"+userName+'\''+
                ",email='"+email+'\''+
                ",password='"+password+'\''+
                ",firstName='"+firstName+'\''+
                ",middleName='"+middleName+'\''+
                ",lastName='"+lastName+'\''+
                ",nativeName='"+nativeName+'\''+
                ",age='"+age+'\''+
                ",address='"+address+'\''+
                ",phoneNumber='"+phoneNumber+'\''+
                ",country='"+country+'\''+
                ')';
    }
}

