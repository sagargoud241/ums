package com.sgr.ums.Mapper;

import com.sgr.ums.Entity.Student;
import com.sgr.ums.RequestModel.AddStudentRequest;
import com.sgr.ums.RequestModel.UpdateStudentRequest;

public class StudentMapper {
    public static Student addStudent (AddStudentRequest request){
        return new Student(
                request.getName(),
                request.getAge(),
                request.getEmail()
        );
    }

    public static Student updateStudent (UpdateStudentRequest request, Student student){
        student.setName(request.getName());
        student.setAge(request.getAge());
        student.setEmail(request.getEmail());
        return student ;
    }


}
