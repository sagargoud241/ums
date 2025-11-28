package com.sgr.ums.Services;

import com.sgr.ums.Authorization.UserDetail;
import com.sgr.ums.Entity.Course;
import com.sgr.ums.Mapper.CourseMapper;
import com.sgr.ums.Repository.CourseRepository;
import com.sgr.ums.RequestModel.AddCourseRequest;
import com.sgr.ums.RequestModel.DeleteCourseRequest;
import com.sgr.ums.RequestModel.UpdateCourseRequest;
import com.sgr.ums.ResponseModel.ApiResponse;
import com.sgr.ums.Utilities.JsonUtils;
import com.sgr.ums.Utilities.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService{
    private static final Logger log = LoggerFactory.getLogger(CourseServiceImpl.class);
    @Autowired
    private UserDetail currentUserUtil;

    private final CourseRepository courseRepository;
    public CourseServiceImpl(CourseRepository courseRepository){
        this.courseRepository=courseRepository;
    }


    @Override
    public ApiResponse<Course> addCourse(AddCourseRequest request) {

        try{
            log.info("AddCourseRequest request is {}", JsonUtils.toJson(request));
            Course course= CourseMapper.addCourse(request);
            course.setCreatedBy(currentUserUtil.getUsername());
            course.setCreatedDate(LocalDateTime.now());
            course.setActive(true);
            course.setDeleted(false);
            courseRepository.save(course);
            return ApiResponse.success(course, "Add SuccessFully");
        } catch (Exception e) {
            log.error("Exception while add course",e.getMessage());
            return ApiResponse.exception(e.getMessage());
        }
    }

    @Override
    public ApiResponse<Course> updateCourse(UpdateCourseRequest request) {
        Optional<Course> optional = courseRepository.findById(request.getId());
        if (optional.isEmpty()) {
            return ApiResponse.failure("Course not found");
        }
        Course course = optional.get();

        CourseMapper.updateCourse(course, request);
        course.setUpdatedBy(Utility.getDefaultUsername());
        course.setUpdatedDate(LocalDateTime.now());
        courseRepository.save(course);
        return ApiResponse.success(course, "Update SuccessFully");
    }

    @Override
    public ApiResponse<Course> deleteCourse(DeleteCourseRequest request) {
        Optional<Course> optional = courseRepository.findById(request.getId());
        if (optional.isEmpty()) {
            return ApiResponse.failure("Course not found");
        }
        Course course=optional.get();
        course.setRemarks(request.getRemarks());
        course.setUpdatedBy(Utility.getDefaultUsername());
        course.setUpdatedDate(LocalDateTime.now());
        course.setActive(false);
        course.setDeleted(true);
        courseRepository.delete(course);
        return ApiResponse.success(null,"Delete SuccessFully");
    }

    @Override
    public ApiResponse<Course> getCourseById(Long id) {
        Optional<Course> optional = courseRepository.findById(id);
        if(optional.isEmpty()){
            return ApiResponse.failure("Course not Found");
        }
        return ApiResponse.success(optional.get(),"Fetch SuccessFully");
    }


    @Override
    public ApiResponse<List<Course>> getAllCourse() {
        List<Course> courses=courseRepository.findAll();
        return ApiResponse.success(courses,"success");
    }


}
