package com.sgr.ums.RequestModel;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateCourseRequest {

    @NotNull(message = "Id is required")
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is Required ")
    private String description;

    @NotNull(message = "CreditHours is required")
    @Min(value = 1, message = "CreditHours must be at least 1")
    private Integer creditHours;

    //getter

    public @NotNull(message = "CreditHours is Required") Integer getCreditHours() {
        return creditHours;
    }

    public @NotBlank(message = "Description is Required ") String getDescription() {
        return description;
    }

    public @NotNull(message = "Id is required") Long getId() {
        return id;
    }

    public @NotBlank(message = "Title is required") String getTitle() {
        return title;
    }

    //setter


    public void setCreditHours(@NotNull(message = "CreditHours is Required") Integer creditHours) {
        this.creditHours = creditHours;
    }

    public void setDescription(@NotBlank(message = "Description is Required ") String description) {
        this.description = description;
    }

    public void setId(@NotNull(message = "Id is required") Long id) {
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


