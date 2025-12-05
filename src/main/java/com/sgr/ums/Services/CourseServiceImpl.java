package com.sgr.ums.Services;

import com.sgr.ums.Authorization.UserDetail;
import com.sgr.ums.Entity.Course;
import com.sgr.ums.Entity.User;
import com.sgr.ums.Mapper.CourseMapper;
import com.sgr.ums.Repository.CourseRepository;
import com.sgr.ums.RequestModel.AddCourseRequest;
import com.sgr.ums.RequestModel.DeleteCourseRequest;
import com.sgr.ums.RequestModel.DeleteListCourseRequest;
import com.sgr.ums.RequestModel.UpdateCourseRequest;
import com.sgr.ums.ResponseModel.ApiResponse;
import com.sgr.ums.Utilities.JsonUtils;
import com.sgr.ums.Utilities.Utility;
import jakarta.validation.Valid;
import org.apache.juli.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    private static final Logger log = LoggerFactory.getLogger(CourseServiceImpl.class);

    @Autowired
    private UserDetail currentUserUtil;
    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    @Override
    public ApiResponse<Course> addCourse(AddCourseRequest request) {

        try {
            log.info("AddCourseRequest request is {}", JsonUtils.toJson(request));
            Course course = CourseMapper.addCourse(request);
            course.setCreatedBy(currentUserUtil.getUsername());
            course.setCreatedDate(LocalDateTime.now());
            course.setActive(true);
            course.setDeleted(false);
            courseRepository.save(course);
            return ApiResponse.success(course, "Add SuccessFully");
        } catch (Exception e) {
            log.error("Exception while add course", e.getMessage());
            return ApiResponse.exception(e.getMessage());
        }
    }

    @Override
    public ApiResponse<Course> updateCourse(UpdateCourseRequest request) {
        try {
            log.info("UpdateCourse Request is {}", JsonUtils.toJson(request));
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
        } catch (Exception e) {
            log.error("Exception while update course", e.getMessage());
            return ApiResponse.exception(e.getMessage());
        }
    }

    @Override
    public ApiResponse<Course> deleteCourse(DeleteCourseRequest request) {
        try {
            log.info("DeleteCourse Request is {}", JsonUtils.toJson(request));
            Optional<Course> optional = courseRepository.findById(request.getId());
            if (optional.isEmpty()) {
                return ApiResponse.failure("Course not found");
            }
            Course course = optional.get();
            course.setRemarks(request.getRemarks());
            course.setUpdatedBy(Utility.getDefaultUsername());
            course.setUpdatedDate(LocalDateTime.now());
            course.setActive(false);
            course.setDeleted(true);
            courseRepository.save(course);
            return ApiResponse.success(null, "Delete SuccessFully");
        } catch (Exception e) {
            log.error("Exception while delete course", e.getMessage());
            return ApiResponse.exception(e.getMessage());
        }
    }

    @Override
    public ApiResponse<Course> getCourseById(Long id) {
        try {
            log.info("GetCourse By Id{}", JsonUtils.toJson(id));
            Optional<Course> optional = courseRepository.findById(id);
            if (optional.isEmpty()) {
                return ApiResponse.failure("Course not Found");
            }
            return ApiResponse.success(optional.get(), "Fetch SuccessFully");
        } catch (Exception e) {
            log.error("Exception while getCourseById", e.getMessage());
            return ApiResponse.exception(e.getMessage());
        }
    }

    @Override
    public ApiResponse<List<Course>> getAllCourse() {
        try {
            List<Course> courses = courseRepository.findAll();
            return ApiResponse.success(courses, "success");
        } catch (Exception e) {
            log.error("Exception while get All Courses", e.getMessage());
            return ApiResponse.exception(e.getMessage());
        }
    }

    @Override
    public ApiResponse<List<Course>> addCoursesInList(List<AddCourseRequest> requests) {
        try {
            log.info("AddCourseRequest list is {}", JsonUtils.toJson(requests));

            // Map each request to a Course entity
            List<Course> courses = requests.stream()
                    .map(req -> {
                        Course course = CourseMapper.addCourse(req); // map single request to entity
                        course.setCreatedBy(Utility.getDefaultUsername());
                        course.setCreatedDate(LocalDateTime.now());
                        course.setActive(true);
                        course.setDeleted(false);
                        return course;
                    })
                    .collect(Collectors.toList());

            // Save all courses at once
            List<Course> savedCourses = courseRepository.saveAll(courses);

            return ApiResponse.success(savedCourses, "Courses added successfully");

        } catch (Exception e) {
            log.error("Exception while adding courses", e);
            return ApiResponse.exception(e.getMessage());
        }
    }


    @Override
    public ApiResponse<List<Course>> updateCoursesInList(List<UpdateCourseRequest> requests) {
        List<Course> updatedCourses = new ArrayList<>();

        try {
            for (UpdateCourseRequest request : requests) {
                Optional<Course> optional = courseRepository.findById(request.getId());
                if (optional.isEmpty()) {
                    // Optionally, skip or throw exception
                    // Here we skip and log
                    log.warn("Course with ID {} not found, skipping update", request.getId());
                    continue;
                }

                Course course = optional.get();

                // Update course fields using your mapper
                CourseMapper.updateCourse(course, request);

                // Update audit fields
                course.setUpdatedBy(Utility.getDefaultUsername());
                course.setUpdatedDate(LocalDateTime.now());

                // Save the updated course
                updatedCourses.add(courseRepository.save(course));
            }

            return ApiResponse.success(updatedCourses, "Courses updated successfully");

        } catch (Exception e) {
            log.error("Exception while updating courses", e);
            return ApiResponse.exception(e.getMessage());
        }
    }


    @Override
    public ApiResponse<List<Course>> deleteCoursesInList(@Valid List<DeleteListCourseRequest> requests) {
        List<Course> deletedCourses = new ArrayList<>();

        try {
            for (DeleteListCourseRequest request : requests) {
                for (Long id : request.getIds()) {
                    Optional<Course> optional = courseRepository.findById(id);
                    if (optional.isEmpty()) {
                        log.warn("Course with ID {} not found, skipping deletion", id);
                        continue; // Skip missing courses
                    }

                    Course course = optional.get();

                    // Soft delete fields
                    course.setRemarks(request.getRemarks());
                    course.setUpdatedBy(Utility.getDefaultUsername());
                    course.setUpdatedDate(LocalDateTime.now());
                    course.setActive(false);
                    course.setDeleted(true);

                    // Save or hard delete
                    courseRepository.save(course); // soft delete
                    // courseRepository.delete(course); // hard delete if needed

                    deletedCourses.add(course);
                }
            }

            return ApiResponse.success(deletedCourses, "Courses deleted successfully");

        } catch (Exception e) {
            log.error("Exception while deleting courses", e);
            return ApiResponse.exception(e.getMessage());
        }
    }


}

