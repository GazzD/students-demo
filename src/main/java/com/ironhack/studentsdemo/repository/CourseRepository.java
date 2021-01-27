package com.ironhack.studentsdemo.repository;

import com.ironhack.studentsdemo.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
    public Optional<Course> findByCourseName(String courseName);
}
