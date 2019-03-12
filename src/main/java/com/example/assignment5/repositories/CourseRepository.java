package com.example.assignment5.repositories;

import com.example.assignment5.model.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course,Long> {
}
