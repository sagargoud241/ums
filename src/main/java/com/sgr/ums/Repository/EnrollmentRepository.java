package com.sgr.ums.Repository;

import com.sgr.ums.Entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment,Long> {
    Optional<Enrollment> findByIdAndIsActive(Long id, boolean isActive);

    List<Enrollment> findByStudentId(Long studentId);
    List<Enrollment> findByCourseId(Long courseId);
  //  List<Enrollment> findAllAndIsActive(boolean isActive);
}
