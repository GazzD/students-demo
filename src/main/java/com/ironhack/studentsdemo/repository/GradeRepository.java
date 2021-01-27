package com.ironhack.studentsdemo.repository;

import com.ironhack.studentsdemo.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer> {
    public List<Grade> findByStudentNameContaining(String name);

//    -- Cantidad de notas por sección
    @Query("SELECT sectionId, COUNT(*) FROM Grade GROUP BY sectionId")
    public List<Object[]> findScoreNumberBySection();

//    -- Promedio de notas por sección entre las secciones cuyo promedio sea sobresalientes (promedio score > 80)
    @Query("SELECT sectionId, AVG(score) FROM Grade GROUP BY sectionId HAVING AVG(score) > ?1")
    public List<Object[]> findScoreAvgBySectionWithScoreGreaterThan(double score);

    @Query("SELECT sectionId, AVG(score) FROM Grade GROUP BY sectionId HAVING AVG(score) > :score")
    public List<Object[]> findScoreAvgBySectionWithScoreGreaterThan2(@Param("score") double score);

}
