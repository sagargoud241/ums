package com.sgr.ums.RequestModel.CourseRequestModel;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AddCourseRequest {

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

    public void setTitle(@NotBlank(message = "Title is required") String title) {
        this.title = title;
    }

    public AddCourseRequest() {
    }

    public AddCourseRequest( String title, String description,Integer creditHours) {

        this.title = title;
        this.description = description;
        this.creditHours = creditHours;
    }

    @Override
    public String toString() {
        return "AddCourseRequest{" +
                ",title='" + title + '\'' +
                ",description='" + description + '\'' +
                ",creditHour='" + creditHours + '\'' +
                '}';

    }
}

