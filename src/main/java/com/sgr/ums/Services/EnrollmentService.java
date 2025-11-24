package com.sgr.ums.Services;

import com.sgr.ums.Entity.Enrollment;
import com.sgr.ums.RequestModel.AddEnrollmentRequest;
import com.sgr.ums.RequestModel.DeleteEnrollmentRequest;
import com.sgr.ums.RequestModel.UpdateEnrollmentRequest;
import com.sgr.ums.ResponseModel.ApiResponse;

import java.util.List;

public interface EnrollmentService {
    ApiResponse<Enrollment> addEnrollment(AddEnrollmentRequest request);
    ApiResponse<Enrollment>updateEnrollment(UpdateEnrollmentRequest request);
    ApiResponse<Enrollment>deleteEnrollment(DeleteEnrollmentRequest request);
    ApiResponse<Enrollment>getEnrollmentById(Long id);
    ApiResponse<List<Enrollment>>getAllEnrollment();
}
