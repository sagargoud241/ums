package com.sgr.ums.Services;


import com.sgr.ums.Entity.Course;
import com.sgr.ums.Entity.Enrollment;
import com.sgr.ums.Entity.Student;
import com.sgr.ums.Repository.CourseRepository;
import com.sgr.ums.Repository.EnrollmentRepository;
import com.sgr.ums.Repository.StudentRepository;
import com.sgr.ums.RequestModel.AddEnrollmentRequest;
import com.sgr.ums.RequestModel.DeleteEnrollmentRequest;
import com.sgr.ums.RequestModel.UpdateEnrollmentRequest;
import com.sgr.ums.ResponseModel.ApiResponse;
import com.sgr.ums.Utilities.Utility;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private EnrollmentRepository enrollmentRepository;
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;

    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public ApiResponse<Enrollment> addEnrollment(AddEnrollmentRequest request) {
        Enrollment enrollment= new Enrollment();//EnrollmentMapper.AddEnrollment(request);

        Optional<Student> student = studentRepository.findById(request.getStudentId());
        if(student==null || student.isEmpty()){
            return ApiResponse.failure("Student not found.");
        }
        enrollment.setStudent(student.get());

        if(student.get().getDeleted()){
            return ApiResponse.failure("Student not found.");
        }
        Optional<Course> course = courseRepository.findById(request.getCourseId());
        if (course==null || course.isEmpty()) {
            return ApiResponse.failure("Course not found");
        }
        if(course.get().getDeleted()){
            return ApiResponse.failure("Course not found.");
        }
        enrollment.setCourse(course.get());

        enrollment.setEnrolledDate(request.getEnrolledDate());
        enrollment.setCreatedBy(Utility.getDefaultUsername());
        enrollment.setCreatedDate(LocalDateTime.now());
        enrollment.setActive(true);
        enrollment.setDeleted(false);
        enrollmentRepository.save(enrollment);
        return ApiResponse.success(enrollment,"Add SuccessFully");
    }

    @Override
    public ApiResponse<Enrollment> updateEnrollment(UpdateEnrollmentRequest request) {
        Optional<Enrollment> optional=enrollmentRepository.findById(request.getId());
        if(optional.isEmpty()) {
            ApiResponse.failure("Enrollment Not Found ");
        }
        Enrollment enrollment=optional.get();
        //EnrollmentMapper.UpdateEnrollment(request,enrollment);
        enrollment.setUpdatedBy(Utility.getDefaultUsername());
        enrollment.setUpdatedDate(LocalDateTime.now());
        enrollmentRepository.save(enrollment);
        return ApiResponse.success(enrollment,"Update SuccessFully");

    }

    @Override
    public ApiResponse<Enrollment> deleteEnrollment(DeleteEnrollmentRequest request) {
        Optional<Enrollment> optional=enrollmentRepository.findById(request.getId());
        if(optional.isEmpty()) {
            return ApiResponse.failure("Enrollment Not Found ");
        }
        Enrollment enrollment=optional.get();
        enrollment.setRemarks(request.getRemarks());
        enrollment.setActive(false);
        enrollment.setDeleted(true);
        enrollment.setUpdatedBy(Utility.getDefaultUsername());
        enrollment.setUpdatedDate(LocalDateTime.now());
        enrollmentRepository.save(enrollment);
        return ApiResponse.success(null,"Delete SuccessFully");
    }


    @Override
    public ApiResponse<Enrollment> getEnrollmentById(Long id) {
        //Optional<Enrollment> optional=enrollmentRepository.findById(id);
        Optional<Enrollment> optional=enrollmentRepository.findByIdAndIsActive(id,true);
        if(optional.isEmpty()) {
            return ApiResponse.failure("Enrollment Not Found ");
        }
//        if(optional.get().getDeleted()) {
//            return ApiResponse.failure("Enrollment Not Found ");
//        }

        return ApiResponse.success(optional.get(),"Success");
    }

    @Override
    public ApiResponse<List<Enrollment>> getAllEnrollment() {
        List<Enrollment> enrollment=enrollmentRepository.findAll();
        //List<Enrollment> enrollment=enrollmentRepository.findAllAndIsActive(true);
        return ApiResponse.success(enrollment,"Success");

    }

}