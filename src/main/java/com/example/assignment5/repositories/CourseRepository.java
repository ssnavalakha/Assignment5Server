package com.example.assignment5.repositories;

import com.example.assignment5.model.Course;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CourseRepository extends CrudRepository<Course,Long> {
    @Modifying
    @Transactional
    @Query("Delete from Course c where c.user.id=:uid")
    public void deleteByUserId(@Param("uid") long uid);
}
