package com.sgr.ums.Controller;

import com.sgr.ums.Entity.Student;
import com.sgr.ums.RequestModel.AddStudentRequest;
import com.sgr.ums.RequestModel.DeleteStudentRequest;
import com.sgr.ums.RequestModel.UpdateStudentRequest;
import com.sgr.ums.ResponseModel.ApiResponse;
import com.sgr.ums.Services.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    ResponseEntity<ApiResponse<Student>>addStudent(@Valid @RequestBody AddStudentRequest request){
        return ResponseEntity.ok(studentService.addStudent(request));
    }

    @PutMapping()
    ResponseEntity<ApiResponse<Student>>updateStudent(@Valid @RequestBody UpdateStudentRequest request){
        return ResponseEntity.ok(studentService.updateStudent(request));
    }

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<Student>>getStudentById(@PathVariable Long id){
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse<Student>>deleteStudentById(@PathVariable Long id){
        return ResponseEntity.ok(studentService.deleteStudentById(id));
    }

    @GetMapping()
    ResponseEntity<ApiResponse<List<Student>>>getAllStudent(){
        return ResponseEntity.ok(studentService.getAllStudent());
    }

    @DeleteMapping()
    ResponseEntity<ApiResponse<Student>>deleteStudent(@Valid @RequestBody DeleteStudentRequest request){
        return ResponseEntity.ok(studentService.deleteStudent(request));
    }
}


