package com.sgr.ums.Controller;

import com.sgr.ums.Entity.Enrollment;
import com.sgr.ums.RequestModel.AddEnrollmentRequest;
import com.sgr.ums.RequestModel.DeleteEnrollmentRequest;
import com.sgr.ums.RequestModel.UpdateEnrollmentRequest;
import com.sgr.ums.ResponseModel.ApiResponse;
import com.sgr.ums.Services.EnrollmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Api/Enrollment")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;


    @PostMapping
    ResponseEntity<ApiResponse<Enrollment>>addEnrollment(@Valid @RequestBody AddEnrollmentRequest request){
        return ResponseEntity.ok(enrollmentService.addEnrollment(request));
    }

    @PutMapping
    ResponseEntity<ApiResponse<Enrollment>>updateEnrollment(@Valid @RequestBody UpdateEnrollmentRequest request){
        return ResponseEntity.ok(enrollmentService.updateEnrollment(request));
    }

    @DeleteMapping()
    ResponseEntity<ApiResponse<Enrollment>>deleteEnrollment(@Valid @RequestBody DeleteEnrollmentRequest request) {
        return ResponseEntity.ok(enrollmentService.deleteEnrollment(request));

    }
    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<Enrollment>>getEnrollmentById(@PathVariable Long id){
        return ResponseEntity.ok(enrollmentService.getEnrollmentById(id));
    }
    @GetMapping()
    ResponseEntity<ApiResponse<List<Enrollment>>>getAllEnrollment(){
        return ResponseEntity.ok(enrollmentService.getAllEnrollment());
    }
}

