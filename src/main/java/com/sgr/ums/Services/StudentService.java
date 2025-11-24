package com.sgr.ums.Services;

import com.sgr.ums.Entity.Student;
import com.sgr.ums.RequestModel.AddStudentRequest;
import com.sgr.ums.RequestModel.DeleteStudentRequest;
import com.sgr.ums.RequestModel.UpdateStudentRequest;
import com.sgr.ums.ResponseModel.ApiResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface StudentService {

    ApiResponse<Student> addStudent(AddStudentRequest request);

    ApiResponse<Student> updateStudent(UpdateStudentRequest request);
    ApiResponse<Student>getStudentById(Long id);
    ApiResponse<Student>deleteStudentById(Long id);
    ApiResponse<List<Student>>getAllStudent();
    ApiResponse<Student> deleteStudent(@Valid DeleteStudentRequest request);
}
