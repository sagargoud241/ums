package com.sgr.ums.Controller;

import com.sgr.ums.Entity.Course;
import com.sgr.ums.RequestModel.CourseRequestModel.AddCourseRequest;
import com.sgr.ums.RequestModel.CourseRequestModel.DeleteCourseRequest;
import com.sgr.ums.RequestModel.DeleteListCourseRequest;
import com.sgr.ums.RequestModel.CourseRequestModel.UpdateCourseRequest;
import com.sgr.ums.ResponseModel.ApiResponse;
import com.sgr.ums.Services.CourseService.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping
    ResponseEntity<ApiResponse<Course>>AddCourse(@Valid @RequestBody AddCourseRequest request){
        return ResponseEntity.ok(courseService.addCourse(request));
    }

    @PostMapping("/save-list")
    ResponseEntity<ApiResponse<List<Course>>>AddCourses(@Valid @RequestBody List<AddCourseRequest> request){
        return ResponseEntity.ok(courseService.addCoursesInList(request));
    }



    @PutMapping
    ResponseEntity<ApiResponse<Course>>UpdateCourse(@Valid @RequestBody UpdateCourseRequest request){
        return ResponseEntity.ok(courseService.updateCourse(request));
    }

    @PutMapping("/update-list")
    public ResponseEntity<ApiResponse<List<Course>>> updateCourses(@Valid @RequestBody List<UpdateCourseRequest> requests) {
    return ResponseEntity.ok(courseService.updateCoursesInList(requests));
    }


    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse<Course>>DeleteCourse(@Valid @RequestBody DeleteCourseRequest request){
        return ResponseEntity.ok(courseService.deleteCourse(request));
    }

    @DeleteMapping("/delete-list")
    public ResponseEntity<ApiResponse<List<Course>>> deleteCourses(@Valid @RequestBody List<DeleteListCourseRequest> requests) {
        return ResponseEntity.ok(courseService.deleteCoursesInList(requests));
    }

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<Course>>getCourseById(@PathVariable Long id){
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @GetMapping()
    ResponseEntity<ApiResponse<List<Course>>>getAllCourse(){
        return ResponseEntity.ok(courseService.getAllCourse());
    }
}
