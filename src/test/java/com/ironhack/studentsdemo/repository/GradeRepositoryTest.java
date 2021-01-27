package com.ironhack.studentsdemo.repository;

import com.ironhack.studentsdemo.model.Course;
import com.ironhack.studentsdemo.model.Grade;
import com.ironhack.studentsdemo.model.Section;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GradeRepositoryTest {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private SectionRepository sectionRepository;


    @BeforeEach
    public void setUp() {
        // Create courses
        courseRepository.save(new Course("CS101", "Intro to Java"));
        courseRepository.save(new Course("CS103", "Databases"));

        // Create sections
        sectionRepository.save(new Section("CS101-A", "CS101", Short.parseShort("1802") , "Balderez"));
        sectionRepository.save(new Section("CS101-B", "CS101", Short.parseShort("1650") , "Su"));
        sectionRepository.save(new Section("CS103-A", "CS103", Short.parseShort("1200") , "Rojas"));
        sectionRepository.save(new Section("CS103-B", "CS103", Short.parseShort("1208") , "Tonno"));

        // Create grades
        gradeRepository.save(new Grade("Maya Charlotte", "CS101-A", 98));
        gradeRepository.save(new Grade("James Fields", "CS101-A", 82));
        gradeRepository.save(new Grade("Michael Alcocer", "CS101-B", 65));
        gradeRepository.save(new Grade("Maya Charlotte", "CS103-A", 89));
        gradeRepository.save(new Grade("Tomas Lacroix", "CS101-A", 99));
        gradeRepository.save(new Grade("Sara Bisat", "CS101-A", 87));
        gradeRepository.save(new Grade("James Fields", "CS101-B", 46));
        gradeRepository.save(new Grade("Helena Sepulvida", "CS103-A", 72));
    }

    @AfterEach
    public void tearDown() {
        gradeRepository.deleteAll();
        sectionRepository.deleteAll();
        courseRepository.deleteAll();
    }

    @Test
    public void findByStudentNameContaining() {
        List<Grade> grades = gradeRepository.findByStudentNameContaining("sat");

        assertEquals(1, grades.size());
        assertEquals("Sara Bisat", grades.get(0).getStudentName());

    }

    @Test
    public void findScoreNumberBySection() {
        List<Object[]> result = gradeRepository.findScoreNumberBySection();

        assertEquals(3, result.size());
        assertEquals("CS101-A", result.get(0)[0]);
        assertEquals(4L, result.get(0)[1]);
    }

    @Test
    public void findScoreAvgBySectionWithScoreGreaterThan() {
        List<Object[]> result = gradeRepository.findScoreAvgBySectionWithScoreGreaterThan2(80);

        assertEquals(2, result.size());
        assertEquals("CS101-A", result.get(0)[0]);
        assertTrue((Double) result.get(0)[1] > 80);
    }
}