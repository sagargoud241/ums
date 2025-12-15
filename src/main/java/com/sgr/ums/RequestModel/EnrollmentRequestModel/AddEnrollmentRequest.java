package com.sgr.ums.RequestModel.EnrollmentRequestModel;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class AddEnrollmentRequest {


        //public Instant EnrolledDate;
        @NotNull(message = "StudentId is Required")
        private Long studentId;

        @NotNull(message = "CourseId is Required")
        private Long courseId;

        @NotNull(message = "EnrolledDate is Required")
        private LocalDate enrolledDate;

        //getter

        public @NotNull(message = "CourseId is Required") Long getCourseId() {
            return courseId;
        }

        @NotNull(message = "EnrolledDate is Required")
        public LocalDate getEnrolledDate() {
            return enrolledDate;
        }

        public @NotNull(message = "StudentId is Required") Long getStudentId() {
            return studentId;
        }


        //setter


        public void setCourseId(@NotNull(message = "CourseId is Required") Long courseId) {
            this.courseId = courseId;
        }



        public void setEnrolledDate(@NotNull(message = "EnrolledDate is Required") LocalDate enrolledDate) {
            this.enrolledDate = enrolledDate;
        }

        public void setStudentId(@NotNull(message = "StudentId is Required") Long studentId) {
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


