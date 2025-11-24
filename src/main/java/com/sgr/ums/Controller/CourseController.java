package com.sgr.ums.Controller;

import com.sgr.ums.Entity.Course;
import com.sgr.ums.RequestModel.AddCourseRequest;
import com.sgr.ums.RequestModel.DeleteCourseRequest;
import com.sgr.ums.RequestModel.UpdateCourseRequest;
import com.sgr.ums.ResponseModel.ApiResponse;
import com.sgr.ums.Services.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping
    ResponseEntity<ApiResponse<Course>>AddCourse(@Valid @RequestBody AddCourseRequest request){
        return ResponseEntity.ok(courseService.addCourse(request));
    }
    @PutMapping
    ResponseEntity<ApiResponse<Course>>UpdateCourse(@Valid @RequestBody UpdateCourseRequest request){
        return ResponseEntity.ok(courseService.updateCourse(request));
    }
    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse<Course>>DeleteCourse(@Valid @RequestBody DeleteCourseRequest request){
        return ResponseEntity.ok(courseService.deleteCourse(request));
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
