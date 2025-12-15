package com.sgr.ums.RequestModel.StudentRequestModel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateStudentRequest {

    @NotNull(message = "Id is Required")
    private Long id;

    @NotBlank(message = "Name is Required ")
    private String name;

    @NotBlank(message="Email is Required ")
    private String email;


    @NotNull(message = "Age is Required")
    private  Integer age;

    @NotBlank(message = "UpdateBy is Required")
    private  String updateBy;

    //getter

    public @NotNull(message = "Age is Required") Integer getAge() {return age;}

    public @NotBlank(message = "Email is Required ") String getEmail() {return email;}

    public @NotNull(message = "Id is Required") Long getId() {return id;}

    public @NotBlank(message = "Name is Required ") String getName() {return name;}

    public @NotBlank(message = "UpdateBy is Required") String getUpdateBy() {return updateBy;}




    //setter


    public void setAge(@NotNull(message = "Age is Required") Integer age) {this.age = age;}

    public void setEmail(@NotBlank(message = "Email is Required ") String email) {this.email=email;}

    public void setId(@NotNull(message = "Id is Required") Long id) {this.id = id;}

    public void setName(@NotBlank(message = "Name is Required ") String name) {this.name = name;}

    public void setUpdateBy(@NotBlank(message = "UpdateBy is Required") String updateBy) {this.updateBy = updateBy;}



    public UpdateStudentRequest(){}

    public UpdateStudentRequest(Integer age, String email, Long id, String name,String updateBy  ) {
        this.age = age;
        this.email = email;
        this.id = id;
        this.name = name;
        this.updateBy=updateBy;
    }


    @Override
    public String toString() {
        return "UpdateStudent{" +
                "id='" + id + '\'' +
                ",name='" + name + '\'' +
                ",email='" + email + '\'' +
                ",age='" + age + '\'' +
                ",updateBy='" + updateBy + '\'' +
                '}';
    }
}


