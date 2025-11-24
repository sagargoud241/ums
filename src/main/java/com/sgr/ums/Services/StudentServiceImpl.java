package com.sgr.ums.Services;

import com.sgr.ums.Entity.Student;
import com.sgr.ums.Mapper.StudentMapper;
import com.sgr.ums.Repository.StudentRepository;
import com.sgr.ums.RequestModel.AddStudentRequest;
import com.sgr.ums.RequestModel.DeleteStudentRequest;
import com.sgr.ums.RequestModel.UpdateStudentRequest;
import com.sgr.ums.ResponseModel.ApiResponse;
import com.sgr.ums.Utilities.Utility;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{
    private final StudentRepository studentRepository;


    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


        @Override
        public ApiResponse<Student> addStudent(AddStudentRequest request) {
            Optional<Student> optional = studentRepository.findByEmail(request.getEmail());
            if (optional!=null && !optional.isEmpty()) {
                return ApiResponse.failure("Email is Already Used");
            }
            Student student = StudentMapper.addStudent(request);
            student.setCreatedBy(Utility.getDefaultUsername());
            student.setActive(true);
            student.setDeleted(false);
            student.setCreatedDate(LocalDateTime.now());
            studentRepository.save(student);
            return ApiResponse.success(student, "Add SuccessFully");
        }

    @Override
    public ApiResponse<Student> updateStudent(UpdateStudentRequest request) {

        Optional<Student> existing = studentRepository.findById(request.getId());
        if (existing == null || existing.isEmpty()) {

            return ApiResponse.failure("Student not found.");
        }
        Student student = existing.get();
        // email unique x aki nai
        if (!student.getEmail().equals(request.getEmail())) {

            Optional<Student> optional = studentRepository.findByEmail(request.getEmail());
            if (optional != null && !optional.isEmpty()) {
                return   ApiResponse.failure("Email is Already Used");
            }

        }

        student = StudentMapper.updateStudent(request,student);
        student.setUpdatedBy(Utility.getDefaultUsername());
        student.setUpdatedDate(LocalDateTime.now());
        studentRepository.save(student);
        return ApiResponse.success(student, "Updated SuccessFully");
    }

    @Override
    public ApiResponse<Student> getStudentById(Long id) {
        Optional<Student> optional = studentRepository.findById(id);
        if (optional.isEmpty()) {
            return   ApiResponse.failure("Student Not Found");
        }
        return ApiResponse.success(optional.get(), "fetch Successfully");
    }


    @Override
    public ApiResponse<Student> deleteStudentById(Long id) {
        Optional<Student> optional = studentRepository.findById(id);
        if(optional.isEmpty()){
            return ApiResponse.failure("Student Not Found");
        }
        Student student=optional.get();
        studentRepository.delete(student);
        return ApiResponse.success(null,"Delete Successfully");
    }

    @Override
    public ApiResponse<List<Student>> getAllStudent() {
        List<Student>student=studentRepository.findAll();
        return ApiResponse.success(student ,"success");

    }

    @Override
    public ApiResponse<Student> deleteStudent(DeleteStudentRequest request) {
        Optional<Student> optional = studentRepository.findById(request.getId());
        if(optional.isEmpty()){
            return ApiResponse.failure("Student Not Found");
        }
        Student student=optional.get();
        student.setRemarks(request.getRemarks());
        student.setActive(false);
        student.setDeleted(true);
        student.setUpdatedBy(Utility.getDefaultUsername());
        student.setUpdatedDate(LocalDateTime.now());
        studentRepository.save(student);
        return ApiResponse.success(null,"Delete Successfully");
    }
}
