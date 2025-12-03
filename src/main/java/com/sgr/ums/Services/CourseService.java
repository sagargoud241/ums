package com.sgr.ums.Services;

import com.sgr.ums.Entity.Course;
import com.sgr.ums.RequestModel.AddCourseRequest;
import com.sgr.ums.RequestModel.DeleteCourseRequest;
import com.sgr.ums.RequestModel.DeleteListCourseRequest;
import com.sgr.ums.RequestModel.UpdateCourseRequest;
import com.sgr.ums.ResponseModel.ApiResponse;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface CourseService {

    ApiResponse<Course> addCourse(AddCourseRequest request);
    ApiResponse<Course>updateCourse(UpdateCourseRequest request);
    ApiResponse<Course>deleteCourse(DeleteCourseRequest request);
    ApiResponse<Course>getCourseById(Long id);
    ApiResponse<List<Course>>getAllCourse();
    ApiResponse<List<Course>> addCoursesInList(@Valid List<AddCourseRequest> requests);
    ApiResponse<List<Course>>updateCoursesInList(@Valid List<UpdateCourseRequest>requests);
    ApiResponse<List<Course>>deleteCoursesInList(@Valid List<DeleteListCourseRequest> requests);
}
