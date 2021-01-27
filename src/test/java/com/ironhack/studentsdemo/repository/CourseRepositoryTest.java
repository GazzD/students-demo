package com.ironhack.studentsdemo.repository;

import com.ironhack.studentsdemo.model.Course;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @BeforeEach
    public void setUp() {
        Course course = new Course("CS105", "Spring Boot");
        Course course2 = new Course("CS106", "Intro to JPA");
        courseRepository.save(course);
        courseRepository.save(course2);
    }

    @AfterEach
    public void tearDown() {
        courseRepository.deleteAll();
    }

    @Test
    public void findAll_CoursesExist_CourseList() {
        List<Course> courseList = courseRepository.findAll();
        assertEquals(2, courseList.size());
    }

    @Test
    public void findByCourseName_ValidCourse_RightCourse() {
        Optional<Course> course = courseRepository.findByCourseName("Spring Boot");
        assertTrue(course.isPresent());
        assertEquals("CS105", course.get().getCourseCode());
    }

}