package com.sgr.ums.RequestModel;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class AddEnrollmentRequest {


        //public Instant EnrolledDate;
        @NotBlank(message = "StudentId is Required")
        private Long studentId;

        @NotBlank(message = "CourseId is Required")
        private Long courseId;

        @NotBlank(message = "EnrolledDate is Required")
        private LocalDate enrolledDate;

        //getter

        public @NotBlank(message = "CourseId is Required") Long getCourseId() {
            return courseId;
        }

        @NotBlank(message = "EnrolledDate is Required")
        public LocalDate getEnrolledDate() {
            return enrolledDate;
        }

        public @NotBlank(message = "StudentId is Required") Long getStudentId() {
            return studentId;
        }


        //setter


        public void setCourseId(@NotBlank(message = "CourseId is Required") Long courseId) {
            this.courseId = courseId;
        }



        public void setEnrolledDate(@NotBlank(message = "EnrolledDate is Required") LocalDate enrolledDate) {
            this.enrolledDate = enrolledDate;
        }

        public void setStudentId(@NotBlank(message = "StudentId is Required") Long studentId) {
            this.studentId = studentId;
        }


        public AddEnrollmentRequest(){}

        public AddEnrollmentRequest( Long studentId,Long courseId, LocalDate enrolledDate) {
            this.studentId = studentId;
            this.courseId = courseId;
            this.enrolledDate = enrolledDate;
        }


        @Override
        public String toString(){
            return  " AddEnrollmentRequest("+
                    ",studentId='"+studentId+'\''+
                    ",courseId='"+courseId+'\''+
                    ",enrolledDate='"+enrolledDate+'\''+
                    '}';
        }


    }


