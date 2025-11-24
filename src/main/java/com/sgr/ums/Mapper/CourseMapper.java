package com.sgr.ums.Mapper;

import com.sgr.ums.Entity.Course;
import com.sgr.ums.RequestModel.AddCourseRequest;
import com.sgr.ums.RequestModel.UpdateCourseRequest;

public class CourseMapper {
    public static Course addCourse(AddCourseRequest request){
        Course course =new Course();
        course.setTitle( request.getTitle());
        course.setDescription(request.getDescription());
        course.setCreditHours( request.getCreditHours());
        return course;

    }

    public static Course updateCourse(Course course, UpdateCourseRequest request ){
        course.setTitle( request.getTitle());
        course.setDescription(request.getDescription());
        course.setCreditHours( request.getCreditHours());
        return course;
    }
}
