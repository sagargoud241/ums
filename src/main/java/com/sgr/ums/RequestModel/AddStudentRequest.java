package com.sgr.ums.RequestModel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AddStudentRequest {


    @NotBlank(message = "Name is Required ")
    private String name;

    @NotBlank(message="Email is Required ")
    private String email;

    @NotNull(message = "Age is Required")
    private  Integer age;




    //getter

    public @NotNull Integer getAge() {return age;}

    public @NotBlank String getEmail() {return email;}

    public @NotBlank String getName() {return name;}




    //setter


    public void setAge (Integer age) {this.age = age;}

    public void setEmail( String email) {this.email = email;}

    public void setName( String name) {this.name = name;}

    public AddStudentRequest(){}

    public AddStudentRequest(Integer age, String email, String name) {
        this.age = age;
        this.email = email;
        this.name = name;
    }

    @Override
    public String toString(){
        return "AddStudent{" +
                "name='" + name + '\'' +
                ",email='" + email + '\'' +
                ",age='" + age +  '\''+
                '}';

    }
}
