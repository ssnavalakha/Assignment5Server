package com.example.assignment5.repositories;

import com.example.assignment5.model.Lesson;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LessonRepository extends CrudRepository<Lesson,Long> {
    @Query("SELECT lesson from Lesson lesson WHERE lesson.module.id=:mid ")
    public List<Lesson> findLessonByMid
            (@Param("mid") long mid);
    @Modifying
    @Transactional
    @Query("Delete from Lesson l where l.module.id=:mid")
    public void deleteByModuleId(@Param("mid") long mid);
}
