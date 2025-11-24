package com.sgr.ums.RequestModel;

import jakarta.validation.constraints.NotBlank;

public class AddCourseRequest {

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

