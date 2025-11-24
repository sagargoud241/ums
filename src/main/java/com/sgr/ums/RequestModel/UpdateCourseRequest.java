package com.sgr.ums.RequestModel;

import jakarta.validation.constraints.NotBlank;

public class UpdateCourseRequest {

    @NotBlank(message = "Id is required")
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is Required ")
    private String description;

    @NotBlank(message = "CreditHours is Required")
    private Integer creditHours;

    //getter

    public @NotBlank(message = "CreditHours is Required") Integer getCreditHours() {
        return creditHours;
    }

    public @NotBlank(message = "Description is Required ") String getDescription() {
        return description;
    }

    public @NotBlank(message = "Id is required") Long getId() {
        return id;
    }

    public @NotBlank(message = "Title is required") String getTitle() {
        return title;
    }

    //setter


    public void setCreditHours(@NotBlank(message = "CreditHours is Required") Integer creditHours) {
        this.creditHours = creditHours;
    }

    public void setDescription(@NotBlank(message = "Description is Required ") String description) {
        this.description = description;
    }

    public void setId(@NotBlank(message = "Id is required") Long id) {
        this.id = id;
    }

    public void setTitle(@NotBlank(message = "Title is required") String title) {
        this.title = title;
    }


    public UpdateCourseRequest(){}

    public UpdateCourseRequest( Long id, String title,Integer creditHours, String description) {

        this.id = id;
        this.title = title;
        this.creditHours = creditHours;
        this.description = description;
    }
    @Override
    public String toString(){
        return "UpdateCourseRequest{" +
                ",id='"+id+'\''+
                ",title='"+title+'\''+
                ",creditHours='"+creditHours+'\''+
                ",description='"+description+'\''+
                '}';

    }
}


